package com.example.reviewmate

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reviewmate.databinding.ItemCommentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MyCommentViewHolder(val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root)

class MyCommentAdapter(val context: Context, val itemList: MutableList<ItemCommentModel>): RecyclerView.Adapter<MyCommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCommentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MyCommentViewHolder(ItemCommentBinding.inflate(layoutInflater))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder:MyCommentViewHolder, position: Int) {
        val data = itemList.get(position)

        holder.binding.run {
            itemCommentView.text=data.text
            itemCommentDateView.text=data.time
            itemEmailView.text=data.user

            itemCommentView.setOnClickListener {
                MyApplication.db.collection("reviews").document(data.reviewId.toString())
                    .get()
                    .addOnSuccessListener { documentSnapshot ->
                        val bundle: Bundle = Bundle()
                        bundle.putString("userEmail", documentSnapshot.getString("email"))
                        bundle.putString("title", documentSnapshot.getString("title"))
                        bundle.putString("content", documentSnapshot.getString("content"))
                        bundle.putString("date", documentSnapshot.getString("date"))
                        bundle.putString("movie", documentSnapshot.getString("movie"))
                        bundle.putString("rate", documentSnapshot.getString("rate"))
                        bundle.putString("movieImage", documentSnapshot.getString("movieImage"))
                        bundle.putString("movieId", documentSnapshot.getString("movieId"))
                        bundle.putString("reviewId", data.reviewId)

                        Intent(context, ReviewDetailActivity::class.java).apply {
                            putExtras(bundle)
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        }.run { context.startActivity(this) }
                    }
            }

            CoroutineScope(Dispatchers.Main).launch {
                val profileImageUrl = MyApplication.getImageUrl(data.user)
                if (!profileImageUrl.isNullOrEmpty()) {
                    // Glide를 사용하여 프로필 이미지 로드
                    Glide.with(context)
                        .load(profileImageUrl)
                        .into(holder.binding.itemImg)

                } else {
                    Toast.makeText(context, "no profile", Toast.LENGTH_SHORT).show()
                    //profileImageUrl=MyApplication.getImageUrl(data.email)
                    Toast.makeText(context, "${profileImageUrl}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun formatTime(dateTime: String): String {
        val dateTimeParts = dateTime.split(" ")
        if (dateTimeParts.size >= 2) {
            val timeParts = dateTimeParts[1].split(":")
            if (timeParts.size >= 2) {
                val hour = timeParts[0]
                val minute = timeParts[1]
                return "$hour:$minute"
            }
        }
        return ""
    }

    fun setData(list: List<ItemCommentModel>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }
}