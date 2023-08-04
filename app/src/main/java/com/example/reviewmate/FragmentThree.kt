package com.example.reviewmate

import android.app.AlertDialog
import android.app.ProgressDialog.show
import android.content.ContentValues.TAG
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.bumptech.glide.Glide
import com.example.reviewmate.MyApplication.Companion.db
import com.example.reviewmate.databinding.FragmentThreeBinding
import com.google.android.play.integrity.internal.c
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentThree.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentThree : Fragment() {


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentThreeBinding
    //lateinit var userLevel : String

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
        // Inflate the layout for this fragment
        binding = FragmentThreeBinding.inflate(inflater, container, false)

        binding.userLevel.text = UserModel().userLevel
//        upLoadProfileImg()
//        Glide.with(requireContext())
//            .load(user.)
//            .into(binding.userProfile)
        //binding.userProfile.setImageResource(R.drawable.hanni_2)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val UserLevel = binding.userLevel.text.toString()
        val IntUL: Int? = UserLevel.toIntOrNull()

        if (IntUL !== null) { // 업로드 & 다이얼로그

            openCharacters(IntUL)
            openDialog(IntUL)

        } else {
            Toast.makeText(requireContext(), "사용자의 레벨을 가져오는데 실패했습니다...", Toast.LENGTH_SHORT).show()
        }


    }

    val characters = arrayOf(
        arrayOf("/profile_images/danielle_1.png", "이름1", "설명1"),
        arrayOf("/profile_images/danielle_2.png", "이름2", "설명2"),
        arrayOf("/profile_images/haerin_1.png", "이름3", "설명3"),
        arrayOf("/profile_images/haerin_2.png", "이름4", "설명4"),
        arrayOf("/profile_images/hanni_1.png", "이름5", "설명5"),
        arrayOf("/profile_images/hanni_2.png", "이름6", "설명6"),
        arrayOf("/profile_images/hyein_1.png", "이름7", "설명7"),
        arrayOf("/profile_images/hyein_2.png", "이름8", "설명8"),
        arrayOf("/profile_images/minji_1.png", "이름9", "설명9")
    )
    fun openCharacters(level : Int) {
        val level = 3 //  테스트용!!!!!!!!!!
        when (level) {
            1 -> {
                binding.level1.setImageResource(R.drawable.danielle_1)
            }
            2 -> {
                binding.level2.setImageResource(R.drawable.danielle_2)
                binding.level2Text.text=characters[1][1]
            }
            3 -> {
                binding.level3.setImageResource(R.drawable.hanni_1)
                binding.level2.setImageResource(R.drawable.danielle_2)
                binding.level2Text.text=characters[1][1]
                binding.level3Text.text=characters[2][1]
            }
            3 -> {
                binding.level3.setImageResource(R.drawable.hanni_1)
                binding.level2.setImageResource(R.drawable.danielle_2)
                binding.level4.setImageResource(R.drawable.hyein_1)
                binding.level2Text.text=characters[1][1]
                binding.level3Text.text=characters[2][1]
                binding.level4Text.text=characters[3][1]
            }// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~9까지
            else -> {
                // 기본 이미지 또는 처리할 로직 설정
            }
    }}


    fun updateUserProfileImage() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid

        if (userId != null) {
            val userDocumentRef =
                FirebaseFirestore.getInstance().collection("users").document(userId)

            userDocumentRef.get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val user = documentSnapshot.toObject(UserModel::class.java)

                        if (user != null && user.imageUrl.isNullOrEmpty()) {
                            // 사용자의 이미지 URL을 가져와서 프로필 이미지로 설정
                            Glide.with(requireContext())
                                .load(user.imageUrl)
                                .into(binding.userProfile)
                        }
                    }
                }
                .addOnFailureListener { exception ->
                    Log.e(TAG, "사용자 데이터 가져오기 중 오류 발생: $exception")
                }
        }
    }

    fun openDialog(level: Int) {

        // 이미지 리소스 식별자 가져오기
        val imgResourceIds = arrayOf(
            R.drawable.danielle_1,
            R.drawable.danielle_2,
            R.drawable.hanni_1,
            R.drawable.hyein_1,
            // ... 나머지 이미지 리소스 ID
        )

        val btnLevels = arrayOf(
            binding.level1,
            binding.level2,
            binding.level3,
            binding.level4,
            binding.level5,
            binding.level6,
            binding.level7,
            binding.level8,
            binding.level9
        )
        val levelBound = if (level <= 9) level else 1
        Toast.makeText(context, "my level $level, ${btnLevels[levelBound - 1]}", Toast.LENGTH_SHORT).show()



        // 모든 버튼에 리스너 설정
        for (i in 0 until levelBound) {
            btnLevels[i].setOnClickListener {
                // 해당 버튼 클릭 이벤트 처리

                AlertDialog.Builder(requireContext()).run {
                    setTitle(characters[i][1])
                    setMessage(characters[i][2]) // 여기 함수로 작성
                    setPositiveButton("프로필 적용하기") { dialog, id ->
                        upLoadProfileImg(characters[i][0]) // 이미지 파일 전달 해줘야함
                        binding.userProfile.setImageResource(imgResourceIds[i])
                    }
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
        }
    }

    fun upLoadProfileImg(strImg : String) {
        // Firebase Storage의 파일 참조 가져오기
        val storageReference =
            FirebaseStorage.getInstance().getReference(strImg)
        Log.d(TAG, "해당 imageUrl${storageReference}")
// 파일의 다운로드 URL 가져오기
        storageReference.downloadUrl.addOnSuccessListener { uri ->
            val downloadUrl = uri.toString()
            Log.d(TAG, "해당 imageUrl${downloadUrl}")
            // 유저 프로필에 저장하기
            val userId = FirebaseAuth.getInstance().currentUser?.uid
            Log.d(TAG, "해당 아이디 ${userId}")
            Toast.makeText(context, "base${userId}", Toast.LENGTH_SHORT).show()
            if (userId != null) {
                val userDocumentRef =
                    FirebaseFirestore.getInstance().collection("users").document(userId)

                userDocumentRef.get()
                    .addOnSuccessListener { documentSnapshot ->
                        if (documentSnapshot.exists()) {
                            val user = documentSnapshot.toObject(UserModel::class.java)

                            if (user != null) {
                                // 이미지 URL을 사용하여 UserModel 업데이트
                                user.imageUrl = downloadUrl


                                // Firestore의 users 컬렉션 업데이트
                                userDocumentRef.set(user, SetOptions.merge())
                                    .addOnSuccessListener {
                                        Glide.with(requireContext())
                                            .load(downloadUrl)
                                            .into(binding.userProfile)  // yourImageView는 이미지를 표시할 ImageView입니다.

                                    }
                                    .addOnFailureListener { exception ->
                                        Log.e(TAG, "사용자 정보 업데이트 중 오류 발생: $exception")
                                    }
                            }
                        } else {
                            Log.d(TAG, "사용자 문서가 존재하지 않습니다.")
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.e(TAG, "사용자 데이터 가져오기 중 오류 발생: $exception")
                    }
            }


            Log.d(TAG, "다운로드 URL: $downloadUrl")
        }.addOnFailureListener { exception ->
            // 다운로드 URL을 가져오는 도중에 오류가 발생한 경우 처리
            Log.e(TAG, "다운로드 URL을 가져오는 중 오류 발생: $exception")
        }
    }


    val alertHandler = object:DialogInterface.OnClickListener {
        override fun onClick(dialog: DialogInterface?, which: Int) {
            when(which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    Log.d("ToyProject", "DialogInterface.BUTTON_POSITIVE")
                }
                DialogInterface.BUTTON_NEGATIVE -> {
                    Log.d("ToyProject", "DialogInterface.BUTTON_NEGATIVE")
                }
                DialogInterface.BUTTON_NEUTRAL -> {
                    Log.d("ToyProject", "DialogInterface.BUTTON_NEUTRAL")
                }
            }
        }
    }
}

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment FragmentThree.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            FragmentThree().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
//}