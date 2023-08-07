package com.example.reviewmate

import android.app.AlertDialog
import android.content.ClipData
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.reviewmate.MyApplication
import com.example.reviewmate.MyApplication.Companion.db
import com.example.reviewmate.MyApplication.Companion.storage
import com.example.reviewmate.databinding.ItemFeedBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import okhttp3.internal.notifyAll

class MyFeedViewHolder(val binding: ItemFeedBinding) : RecyclerView.ViewHolder(binding.root)

class MyFeedAdapter(val context: Context, val itemList: MutableList<ItemFeedModel>): RecyclerView.Adapter<MyFeedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyFeedViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MyFeedViewHolder(ItemFeedBinding.inflate(layoutInflater))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyFeedViewHolder, position: Int) {
        val data = itemList.get(position)

        holder.binding.run {
            itemEmailView.text=data.email
            itemTitleView.text=data.title
            itemContentView.text=data.content
            itemDateView.text=data.date
            itemMovieView.text=data.movie
            itemRateView.text=data.rate

            itemTitleView.setOnClickListener {
                val bundle : Bundle = Bundle()
                bundle.putString("email", data.email)
                bundle.putString("title", data.title)
                bundle.putString("content", data.content)
                bundle.putString("date", data.date)
                bundle.putString("movie", data.movie)
                bundle.putString("rate", data.rate)
                bundle.putString("userEmail", data.email)
                bundle.putString("date", data.date)

                Intent(context, ReviewDetailActivity::class.java).apply {
                    putExtras(bundle)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { context.startActivity(this) }
            }

            val alertHandler = object: DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    when(which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            db.collection("reviews").document("${data.docId}")
                                .delete()
                                .addOnSuccessListener { Toast.makeText(context, "삭제가 완료되었습니다.", Toast.LENGTH_SHORT).show() }
                                .addOnFailureListener { Toast.makeText(context, "삭제가 실패하였습니다.", Toast.LENGTH_SHORT).show() }

                            storage.getReference().child("images").child("${data.docId!!}.jpg")
                                .delete()
                            notifyItemRemoved(position)
                        }
                        DialogInterface.BUTTON_NEGATIVE -> {
                            Log.d("ToyProject", "DialogInterface.BUTTON_NEGATIVE")
                        }
                    }
                }
            }

            if(itemEmailView.text == MyApplication.email){
                reviewDelete.visibility = View.VISIBLE
            }

            reviewDelete.setOnClickListener {
                AlertDialog.Builder(context).run{
                    setTitle("정말 삭제하시겠습니까?")
                    setMessage("한 번 삭제하면 되돌릴 수 없습니다.")
                    setNegativeButton("Cancle", alertHandler)
                    setPositiveButton("Yes", alertHandler)
                    show()
                }
            }
        }

        //스토리지 이미지 다운로드........................
        val imageRef = MyApplication.storage.reference.child("images/${data.docId}.jpg")
        imageRef.downloadUrl.addOnCompleteListener{ task ->
            if(task.isSuccessful){
//                // 다운로드 이미지를 ImageView에 보여줌
                GlideApp.with(context)
                    .load(task.result)
                    .into(holder.binding.itemImageView)
            }
        }
    }

}

