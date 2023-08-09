package com.example.reviewmate

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reviewmate.MyApplication.Companion.auth
import com.example.reviewmate.MyApplication.Companion.db
import com.example.reviewmate.databinding.FragmentFiveReviewListBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import okhttp3.internal.notify
import okhttp3.internal.notifyAll


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentFive_ReviewList.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentFive_ReviewList : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentFiveReviewListBinding

    private var reviewCountListener: ListenerRegistration? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFiveReviewListBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)
        setupReviewCountListener()

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_list, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete_all -> {
                showDeleteConfirmationDialog()
//                deleteAllUserReview(MyApplication.email.toString())
                return true
            }
            // 다른 메뉴 항목 처리를 추가하려면 여기에 추가합니다.
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onStart() {
        super.onStart()
        if(MyApplication.checkAuth()){
            MyApplication.db.collection("reviews")
                //.whereEqualTo("email", MyApplication.email)
                .orderBy("date", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener { result ->
                    val itemList = mutableListOf<ItemFeedModel>()
                    for(document in result){
                        val item = document.toObject(ItemFeedModel::class.java)
                        if(MyApplication.email.equals(item.email)){
                            //Toast.makeText(context, "${MyApplication.email}", Toast.LENGTH_SHORT).show()
                            item.docId = document.id
                            itemList.add(item)
                        }
                    }
                    binding.feedRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                    binding.feedRecyclerView.adapter = MyFeedAdapter(requireContext(), itemList)
                    //goReviewDtaill()
                }
                .addOnFailureListener{
                    Toast.makeText(requireContext(), "데이터 획득 실패", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun deleteAllUserReview(userEmail: String) {
        val currentUser = auth.currentUser

        currentUser?.let {
            val userEmail = currentUser.email

            db.collection("reviews")
                .whereEqualTo("email", userEmail)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    for (documentSnapshot in querySnapshot.documents) {
                        val reviewId = documentSnapshot.id
                        db.collection("reviews").document(reviewId)
                            .delete()
                            .addOnSuccessListener {
                                Toast.makeText(requireContext(), "리뷰가 모두 삭제되었습니다.", Toast.LENGTH_SHORT).show()
                                updateUserReviewCount(auth.uid.toString(), 0)
                            }
                            .addOnFailureListener {  }
                    }
                }
                .addOnFailureListener {  }
        }
    }

    private fun updateUserReviewCount(uid: String, newCount: Long) {
        val userRef = db.collection("users").document(uid)

        userRef.update("userReviewCount", newCount)
            .addOnSuccessListener {
                // userReviewCount 업데이트 성공 시 처리
            }
            .addOnFailureListener { e ->
                // userReviewCount 업데이트 실패 시 처리
            }
    }

    private fun showDeleteConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("정말로 삭제하시겠습니까?")
            .setPositiveButton("삭제") { _, _ ->
                deleteAllUserReview(MyApplication.email.toString())
            }
            .setNegativeButton("취소") { _, _ ->
                // "취소" 버튼 클릭 시, 아무 동작 없음
            }
            .create()
            .show()
    }

    private fun setupReviewCountListener() {
        val currentUser = auth.currentUser

        currentUser?.let {
            val uid = currentUser.uid

            val userRef = db.collection("users").document(uid)
            reviewCountListener = userRef.addSnapshotListener { snapshot, error ->
                if (error != null) {
                    // 리스너에서 오류 처리
                    return@addSnapshotListener
                }
                if(snapshot != null && snapshot.exists()) {
                    val zero: Long = 0
                    val reviewCount = snapshot.getLong("userReviewCount") ?: 0

                    if(reviewCount != zero){
                        binding.textView.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // 리스너 해제
        reviewCountListener?.remove()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentFive_ReviewList.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentFive_ReviewList().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }


    }
}