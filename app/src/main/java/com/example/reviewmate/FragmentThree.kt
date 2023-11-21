package com.example.reviewmate
import android.app.AlertDialog
import android.app.ProgressDialog.show
import android.content.ContentValues.TAG
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import com.example.reviewmate.MyApplication.Companion.auth
import com.example.reviewmate.MyApplication.Companion.db
import com.example.reviewmate.MyApplication.Companion.storage
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
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class FragmentThree : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentThreeBinding
    private lateinit var imageView: ImageView
    // var imageView : ImageView
    //lateinit var userLevel : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }
    override fun onStart() {
        super.onStart()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentThreeBinding.inflate(inflater, container, false)

        fetchLevel()

        imageView = binding.userProfile


        CoroutineScope(Dispatchers.Main).launch {
            val imageUrl =  MyApplication.getImageUrl(MyApplication.email).toString()
            imageView = binding.userProfile
            if( imageUrl != null){
                Glide.with(requireContext())
                    .load(imageUrl)
                    .into(binding.userProfile)
            }
        }


        return binding.root
    }

    private fun fetchLevel() {
        val currentUser = auth.currentUser
        var userLevel :String? = null
        currentUser?.let {
            val userId = currentUser.uid

            val userRef = db.collection("users").document(userId)
            userRef.get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {

                        userLevel = documentSnapshot.getString("userLevel")
                        if (userLevel != null) { // 업로드 & 다이얼로그
                            binding.userLevelTextView.text = userLevel
                            openCharacters(Integer.parseInt(userLevel))
                            openDialog(Integer.parseInt(userLevel))

                        } else {
                            Toast.makeText(requireContext(), "사용자의 레벨을 가져오는데 실패했습니다...", Toast.LENGTH_SHORT).show()
                        }
                        
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(requireContext(), "사용자의 레벨을 가져오지 못했습니다.", Toast.LENGTH_SHORT).show()
                }
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
                                val storageReference = storage.getReferenceFromUrl(imageUrl)
                                // 이미지 다운로드 및 처리 로직

                                storageReference.getBytes(1024 * 1024)
                                    .addOnSuccessListener { bytes ->
                                        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                                        imageView.setImageBitmap(bitmap)
                                    }
                                    .addOnFailureListener { exception ->
                                        // 다운로드 실패 처리
                                        // 예를 들어, 에러 로그를 출력하거나 기본 이미지를 표시할 수 있습니다.
                                    }
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



    }


    val characters = arrayOf(
        arrayOf("/profile_images/level_1.png", "하품하는 고양이", "하품하고 피곤해 보이는 고양이다."),
        arrayOf("/profile_images/level_2.png", "그루밍하는 고양이", "털을 그루밍하는 고양이다."),
        arrayOf("/profile_images/level_3.png", "경계하는 고양이", "호기심 가득한 눈으로 주변을 늘 경계하는 고양이다."),
        arrayOf("/profile_images/level_4.png", "하품하는 고양이", "졸려서 하품하는 검정색 고양이다."),
        arrayOf("/profile_images/level_5.png", "삼색콩떡 고양이", "독특한 삼색 모습으로 사람들의 관심을 끄는 고양이다."),
        arrayOf("/profile_images/level_6.png", "멍때리는 고양이", "눈을 반쯤 감고 먼 곳을 응시하는 듯한 고요한 고양이다."),
        arrayOf("/profile_images/level_7.png", "노려보는 고양이", "까칠한 느낌으로 주변을 바라보는 고양이다."),
        arrayOf("/profile_images/level_8.png", "햇빛쬐는 고양이", "따뜻한 햇살 아래에서 편안함을 느끼는 고양이다."),
        arrayOf("/profile_images/level_9.png", "미소짓는 고양이", "마치 미소를 짓는 듯한 표정으로 귀여움을 뽐내는 고양이다.")
    )


    val imgResourceIds = arrayOf(
        R.drawable.level_1,
        R.drawable.level_2,
        R.drawable.level_3,
        R.drawable.level_4,
        R.drawable.level_5,
        R.drawable.level_6,
        R.drawable.level_7,
        R.drawable.level_8,
        R.drawable.level_9
    )

    fun openLevel1() {
        binding.level1.setImageResource(R.drawable.level_1)
    }
    fun openLevel2() {
        binding.level2.setImageResource(R.drawable.level_2)
        binding.level2Text.text=characters[1][1]
    }
    fun openLevel3() {
        openLevel2()
        binding.level3.setImageResource(R.drawable.level_3)
        binding.level3Text.text=characters[2][1]
    }
    fun openLevel4() {
        openLevel3()
        binding.level4.setImageResource(R.drawable.level_4)
        binding.level4Text.text=characters[3][1]
    }
    fun openLevel5() {
        openLevel4()
        binding.level5.setImageResource(R.drawable.level_5)
        binding.level5Text.text=characters[4][1]
    }
    fun openLevel6() {
        openLevel5()
        binding.level6.setImageResource(R.drawable.level_6)
        binding.level6Text.text=characters[5][1]
    }
    fun openLevel7() {
        openLevel6()
        binding.level7.setImageResource(R.drawable.level_7)
        binding.level7Text.text=characters[6][1]
    }
    fun openLevel8() {
        openLevel7()
        binding.level8.setImageResource(R.drawable.level_8)
        binding.level8Text.text=characters[7][1]
    }
    fun openLevel9() {
        openLevel8()
        binding.level9.setImageResource(R.drawable.level_9)
        binding.level9Text.text=characters[8][1]
    }

    fun openCharacters(level : Int) {
        val level = binding.userLevelTextView.text.toString()
        val intLevel = Integer.parseInt(level)
        when (intLevel) {
            1 -> openLevel1()
            2-> openLevel2()
            3-> openLevel3()
            4 -> openLevel4()
            5 -> openLevel5()
            6 -> openLevel6()
            7 -> openLevel7()
            8 -> openLevel8()
            9 -> openLevel9()
            else -> {
                // 기본 이미지 또는 처리할 로직 설정
            }
        }}


    fun openDialog(level: Int) {
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
                    setNegativeButton("닫기", alertHandler)
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