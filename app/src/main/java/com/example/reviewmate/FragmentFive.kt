package com.example.reviewmate

import android.content.ContentValues
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.example.reviewmate.databinding.FragmentFiveBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentFive.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentFive : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentFiveBinding
    lateinit var profile: ImageView
    private lateinit var imageView: ImageView
    private var  imageUrl : String? = null


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

        profile = binding.userProfile
//        downloadAndDisplayImage()

        // button 클릭시 fragmenrFive_ReviwList로 이동
        binding.btnMove.setOnClickListener { // 람다식 리스너 setOnclickListener{}
            var bundle : Bundle = Bundle()
            bundle.putString("fromFrag", "프래그먼트1")
            val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            val fragmentfive_review: Fragment = FragmentFive_ReviewList()
            fragmentfive_review.arguments = bundle
            transaction.replace(R.id.main_layout, fragmentfive_review)
            transaction.addToBackStack(null)
            transaction.commit()
        }

//        binding.myComments.setOnClickListener {
//            var bundle : Bundle = Bundle()
//            bundle.putString("fromFrag", "프래그먼트2")
//            val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
//            val fragmentfive_comment: Fragment = FragmentFive_CommentList()
//            fragmentfive_comment.arguments = bundle
//            transaction.replace(R.id.main_layout, fragmentfive_comment)
//            transaction.addToBackStack(null)
//            transaction.commit()
//        }

        binding.myLikedmovies.setOnClickListener {
            var bundle : Bundle = Bundle()
            bundle.putString("fromFrag", "프래그먼트1")
            val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            val fragmentfive_likedmovie: Fragment = FragmentFive_LikedMovieList()
            fragmentfive_likedmovie.arguments = bundle
            transaction.replace(R.id.main_layout, fragmentfive_likedmovie)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        if(MyApplication.checkAuth()){
            binding.CertifyEmailView.text = "${MyApplication.email}"
        }

        //val imageUrl : String = MyApplication.imageurl.toString()

        CoroutineScope(Dispatchers.Main).launch {
            imageUrl =  MyApplication.getImageUrl(MyApplication.email).toString()
            imageView = binding.userProfile
            if( imageUrl != null){
                Glide.with(requireContext())
                    .load(imageUrl)
                    .into(binding.userProfile)
            }
        }



        binding.chatListToolbar.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId){
                R.id.menu_logout -> {
                    logout()
                    return@setOnMenuItemClickListener true
                }
                else -> return@setOnMenuItemClickListener true
            }
        }

        binding.introDevelopers.setOnClickListener {
            val intent = Intent(requireContext(), IntroActivity::class.java)
            startActivity(intent)
        }

        binding.report.setOnClickListener {
            sendEmail()
        }

        binding.opinion.setOnClickListener {
            val intent = Intent(requireContext(), OpinionActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    private fun sendEmail() {
        val recipientEmail = "reviewmate2023@gmail.com" // 수신자 이메일 주소를 여기에 입력합니다.
        val subject = "[Android][리뷰메이트]Report"
        val message = """
            
            
            
            
                        ------------------------------
                        - App Version: 1.0.0
                        - User Account: ${MyApplication.email}
                    """.trimIndent()


        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$recipientEmail")
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, message)
        }

        if (intent.resolveActivity(requireContext().packageManager) != null) {
            startActivity(intent)
        } else {
            // 이메일 클라이언트 앱이 설치되어 있지 않은 경우 에러 메시지를 표시할 수 있습니다.
            // 이 부분을 원하는 방식으로 처리하세요.
            Toast.makeText(requireContext(), "이메일 전송 오류", Toast.LENGTH_SHORT).show()
        }
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