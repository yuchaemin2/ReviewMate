package com.example.reviewmate

import android.content.ContentValues
import android.content.Intent
import android.graphics.BitmapFactory
import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.FragmentTransaction
import com.example.reviewmate.databinding.FragmentFiveBinding
import com.example.reviewmate.databinding.FragmentFourBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentFive : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentFiveBinding
    private lateinit var profile: ImageView



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
        binding = FragmentFiveBinding.inflate(inflater, container, false)
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        profile = binding.userProfile // 사용자의 프로필 가져옴
        downloadAndDisplayImage()
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        // button 클릭시 fragmenrFive_ReviwList로 이동
        var btn_move: Button = binding.btnMove
        btn_move.setOnClickListener { // 람다식 리스너 setOnclickListener{}
                 var bundle : Bundle = Bundle()
                  bundle.putString("fromFrag", "프래그먼트1")
                  val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                  val fragmentfive_review: Fragment = FragmentFive_ReviewList()
                    fragmentfive_review.arguments = bundle
            transaction.replace(R.id.main_layout, fragmentfive_review)
            transaction.addToBackStack(null)
            transaction.commit()

        }

        // 로그아웃 버튼을 레이아웃에서 찾아서 클릭 리스너를 추가
        val logoutButton = binding.logoutButton
        logoutButton.setOnClickListener {
            logout()
        }
        return binding.root
    }
    private fun downloadAndDisplayImage() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val userDocumentRef =
                FirebaseFirestore.getInstance().collection("users").document(userId)
            var imageUrl: String? = null

            userDocumentRef.get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val user = documentSnapshot.toObject(UserModel::class.java)

                        if (!user?.imageUrl.isNullOrEmpty()) {
                            user?.imageUrl?.let { imageUrl ->
                                val storageReference = MyApplication.storage.getReferenceFromUrl(imageUrl)
                                // 이미지 다운로드 및 처리 로직

                                storageReference.getBytes(1024 * 1024)
                                    .addOnSuccessListener { bytes ->
                                        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                                        profile.setImageBitmap(bitmap)
                                    }
                                    .addOnFailureListener { exception ->
                                        // 다운로드 실패 처리
                                        // 예를 들어, 에러 로그를 출력하거나 기본 이미지를 표시할 수 있습니다.
                                    }
                            }
                        }
                    } else {
                        Log.d(ContentValues.TAG, "사용자 문서가 존재하지 않습니다.")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.e(ContentValues.TAG, "사용자 데이터 가져오기 중 오류 발생: $exception")
                }

        }



    }


    private fun loadFragment(fragment: Fragment) {
        val transaction = childFragmentManager.beginTransaction() // fragment-fragment는 chid
        transaction.replace(R.id.five_layout, fragment)
        transaction.addToBackStack(null) // Optional: Add the fragment to the back stack
        transaction.commit()
    }


    // 로그아웃 로직을 처리하는 메서드
    private fun logout() {
        val intent = Intent(requireContext(), AuthActivity::class.java)
        startActivity(intent)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentFive.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentFive().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}