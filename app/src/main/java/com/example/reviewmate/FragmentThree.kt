package com.example.reviewmate

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.example.reviewmate.databinding.FragmentThreeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
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

//    var auth : FirebaseAuth? = null
//    var db : FirebaseFirestore? = null
//    var storage : FirebaseStorage? = null
//
//    private var viewProfile : View? = null
//    var pickImage = 0
//    val uriPhoto : Uri? = null

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

//        binding.level1.setOnClickListener{
//            var photoPickerIntent = Intent(Intent.ACTION_PICK)
//            photoPickerIntent.type = "image/profile/*"
//            startActivityForResult(photoPickerIntent, pickImage)
//        }

        return binding.root
    }

//    private fun ProfileUpload(view : View){
//        var timeStamp = SimpleDateFormat("yyyymmdd-hhmmss").format(Date())
//        var imgFileName = "IMAGE_PROFILE_" + timeStamp + "_.png"
//        var storageRef = MyApplication.storage?.reference?.child("images/profile")?.child(imgFileName)
//
//        storageRef?.putFile(uriPhoto!!)?.addOnSuccessListener {
//            storageRef.downloadUrl.addOnSuccessListener {uri ->
//                var userInfo = UserModel()
//
//                userInfo.imageUrl = uri.toString()
//
//                MyApplication.db?.collection("users")?.document(MyApplication.auth?.uid.toString())?.update("imageUrl", userInfo.imageUrl.toString())
//            }
//        }
//    }

    override fun onStart() {
        super.onStart()

        val UserLevel = binding.userLevel.text.toString()
        val IntUL: Int? = UserLevel.toIntOrNull()

        if(IntUL !== null){
            if(IntUL === 1){
                changeProfile("Level1")
            }
            if(IntUL === 2){
                changeVisibility("Level2")
                changeProfile("Level2")
            } else if(IntUL === 3){
                changeVisibility("Level3")
                changeProfile("Level3")
            } else if(IntUL === 4){
                changeVisibility("Level4")
                changeProfile("Level4")
            } else if(IntUL === 5){
                changeVisibility("Level5")
                changeProfile("Level5")
            } else if(IntUL === 6){
                changeVisibility("Level6")
                changeProfile("Level6")
            } else if(IntUL === 7){
                changeVisibility("Level7")
                changeProfile("Level7")
            } else if(IntUL === 8){
                changeVisibility("Level8")
                changeProfile("Level8")
            } else if(IntUL === 9){
                changeVisibility("Level9")
                changeProfile("Level9")
            }
        } else {
            Toast.makeText(requireContext(),"사용자의 레벨을 가져오는데 실패했습니다...", Toast.LENGTH_SHORT).show()
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


    fun changeProfile(mode: String){
        if(mode.equals("Level1")){
            binding.level1.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level1Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.danielle_1)
//                            ProfileUpload(binding.userProfile)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
        } else if(mode.equals("Level2")){
            binding.level1.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level1Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.danielle_1)
//                            ProfileUpload(binding.userProfile)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
            binding.level2.setOnClickListener{
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle(binding.level2Text.text.toString())
                    .setMessage("영화의 매력을 알고있다.")
                    .setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.haerin_1)
//                            ProfileUpload(binding.userProfile)
                        })
                    .setNegativeButton("OK", alertHandler)
                builder.show()
            }
        }else if(mode.equals("Level3")){
            binding.level1.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level1Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.danielle_1)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
            binding.level2.setOnClickListener{
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle(binding.level2Text.text.toString())
                    .setMessage("영화의 매력을 알고있다.")
                    .setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.haerin_1)
                        })
                    .setNegativeButton("OK", alertHandler)
                builder.show()
            }
            binding.level3.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level3Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.hanni_1)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
        }else if(mode.equals("Level4")){
            binding.level1.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level1Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.danielle_1)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
            binding.level2.setOnClickListener{
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle(binding.level2Text.text.toString())
                    .setMessage("영화의 매력을 알고있다.")
                    .setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.haerin_1)
                        })
                    .setNegativeButton("OK", alertHandler)
                builder.show()
            }
            binding.level3.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level3Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.hanni_1)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
            binding.level4.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level4Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.hyein_1)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
        }else if(mode.equals("Level5")){
            binding.level1.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level1Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.danielle_1)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
            binding.level2.setOnClickListener{
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle(binding.level2Text.text.toString())
                    .setMessage("영화의 매력을 알고있다.")
                    .setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.haerin_1)
                        })
                    .setNegativeButton("OK", alertHandler)
                builder.show()
            }
            binding.level3.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level3Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.hanni_1)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
            binding.level4.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level4Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.hyein_1)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
            binding.level5.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level5Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.minji_1)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
        }else if(mode.equals("Level6")){
            binding.level1.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level1Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.danielle_1)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
            binding.level2.setOnClickListener{
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle(binding.level2Text.text.toString())
                    .setMessage("영화의 매력을 알고있다.")
                    .setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.haerin_1)
                        })
                    .setNegativeButton("OK", alertHandler)
                builder.show()
            }
            binding.level3.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level3Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.hanni_1)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
            binding.level4.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level4Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.hyein_1)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
            binding.level5.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level5Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.minji_1)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
            binding.level6.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level6Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.danielle_2)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
        }else if(mode.equals("Level7")){
            binding.level1.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level1Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.danielle_1)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
            binding.level2.setOnClickListener{
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle(binding.level2Text.text.toString())
                    .setMessage("영화의 매력을 알고있다.")
                    .setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.haerin_1)
                        })
                    .setNegativeButton("OK", alertHandler)
                builder.show()
            }
            binding.level3.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level3Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.hanni_1)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
            binding.level4.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level4Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.hyein_1)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
            binding.level5.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level5Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.minji_1)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
            binding.level6.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level6Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.danielle_2)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
            binding.level7.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level7Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.haerin_2)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
        }else if(mode.equals("Level8")){
            binding.level1.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level1Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.danielle_1)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
            binding.level2.setOnClickListener{
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle(binding.level2Text.text.toString())
                    .setMessage("영화의 매력을 알고있다.")
                    .setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.haerin_1)
                        })
                    .setNegativeButton("OK", alertHandler)
                builder.show()
            }
            binding.level3.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level3Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.hanni_1)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
            binding.level4.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level4Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.hyein_1)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
            binding.level5.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level5Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.minji_1)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
            binding.level6.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level6Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.danielle_2)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
            binding.level7.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level7Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.haerin_2)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
            binding.level8.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level8Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.hanni_2)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
        }else if(mode.equals("Level9")){
            binding.level1.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level1Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.danielle_1)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
            binding.level2.setOnClickListener{
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle(binding.level2Text.text.toString())
                    .setMessage("영화의 매력을 알고있다.")
                    .setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.haerin_1)
                        })
                    .setNegativeButton("OK", alertHandler)
                builder.show()
            }
            binding.level3.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level3Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.hanni_1)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
            binding.level4.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level4Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.hyein_1)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
            binding.level5.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level5Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.minji_1)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
            binding.level6.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level6Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.danielle_2)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
            binding.level7.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level7Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.haerin_2)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
            binding.level8.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level8Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.hanni_2)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
            binding.level9.setOnClickListener{
                AlertDialog.Builder(requireContext()).run{
                    setTitle(binding.level9Text.text.toString())
                    setMessage("영화의 매력을 점차 알아가고 있다.")
                    setPositiveButton("프로필 적용하기",
                        DialogInterface.OnClickListener{ dialog, id ->
                            binding.userProfile.setImageResource(R.drawable.hyein_2)
                        })
                    setNegativeButton("OK", alertHandler)
                    show()
                }
            }
        }
    }

    fun changeVisibility(mode: String){
        if(mode.equals("Level2")){
            binding.level2.setImageResource(R.drawable.haerin_1)
            binding.level2Text.text = "영화, 나의 사랑"
        }else if(mode.equals("Level3")){
            binding.level2.setImageResource(R.drawable.haerin_1)
            binding.level3.setImageResource(R.drawable.hanni_1)
            binding.level2Text.text = "영화, 나의 사랑"
            binding.level3Text.text = "영화, 나의 빛"
        }else if(mode.equals("Level4")){
            binding.level2.setImageResource(R.drawable.haerin_1)
            binding.level3.setImageResource(R.drawable.hanni_1)
            binding.level4.setImageResource(R.drawable.hyein_1)
            binding.level2Text.text = "영화, 나의 사랑"
            binding.level3Text.text = "영화, 나의 빛"
            binding.level4Text.text = "영화, 나의 어둠"
        }else if(mode.equals("Level5")){
            binding.level2.setImageResource(R.drawable.haerin_1)
            binding.level3.setImageResource(R.drawable.hanni_1)
            binding.level4.setImageResource(R.drawable.hyein_1)
            binding.level5.setImageResource(R.drawable.minji_1)
            binding.level2Text.text = "영화, 나의 사랑"
            binding.level3Text.text = "영화, 나의 빛"
            binding.level4Text.text = "영화, 나의 어둠"
            binding.level5Text.text = "영화, 나의 삶"
        }else if(mode.equals("Level6")){
            binding.level2.setImageResource(R.drawable.haerin_1)
            binding.level3.setImageResource(R.drawable.hanni_1)
            binding.level4.setImageResource(R.drawable.hyein_1)
            binding.level5.setImageResource(R.drawable.minji_1)
            binding.level6.setImageResource(R.drawable.danielle_2)
            binding.level2Text.text = "영화, 나의 사랑"
            binding.level3Text.text = "영화, 나의 빛"
            binding.level4Text.text = "영화, 나의 어둠"
            binding.level5Text.text = "영화, 나의 삶"
            binding.level6Text.text = "영화, 나의 슬픔"
        }else if(mode.equals("Level7")){
            binding.level2.setImageResource(R.drawable.haerin_1)
            binding.level3.setImageResource(R.drawable.hanni_1)
            binding.level4.setImageResource(R.drawable.hyein_1)
            binding.level5.setImageResource(R.drawable.minji_1)
            binding.level6.setImageResource(R.drawable.danielle_2)
            binding.level7.setImageResource(R.drawable.haerin_2)
            binding.level2Text.text = "영화, 나의 사랑"
            binding.level3Text.text = "영화, 나의 빛"
            binding.level4Text.text = "영화, 나의 어둠"
            binding.level5Text.text = "영화, 나의 삶"
            binding.level6Text.text = "영화, 나의 슬픔"
            binding.level7Text.text = "영화, 나의 안식"
        }else if(mode.equals("Level8")){
            binding.level2.setImageResource(R.drawable.haerin_1)
            binding.level3.setImageResource(R.drawable.hanni_1)
            binding.level4.setImageResource(R.drawable.hyein_1)
            binding.level5.setImageResource(R.drawable.minji_1)
            binding.level6.setImageResource(R.drawable.danielle_2)
            binding.level7.setImageResource(R.drawable.haerin_2)
            binding.level8.setImageResource(R.drawable.hanni_2)
            binding.level2Text.text = "영화, 나의 사랑"
            binding.level3Text.text = "영화, 나의 빛"
            binding.level4Text.text = "영화, 나의 어둠"
            binding.level5Text.text = "영화, 나의 삶"
            binding.level6Text.text = "영화, 나의 슬픔"
            binding.level7Text.text = "영화, 나의 안식"
            binding.level8Text.text = "영화, 나의 구원"
        }else if(mode.equals("Level9")){
            binding.level2.setImageResource(R.drawable.haerin_1)
            binding.level3.setImageResource(R.drawable.hanni_1)
            binding.level4.setImageResource(R.drawable.hyein_1)
            binding.level5.setImageResource(R.drawable.minji_1)
            binding.level6.setImageResource(R.drawable.danielle_2)
            binding.level7.setImageResource(R.drawable.haerin_2)
            binding.level8.setImageResource(R.drawable.hanni_2)
            binding.level9.setImageResource(R.drawable.hyein_2)
            binding.level2Text.text = "영화, 나의 사랑"
            binding.level3Text.text = "영화, 나의 빛"
            binding.level4Text.text = "영화, 나의 어둠"
            binding.level5Text.text = "영화, 나의 삶"
            binding.level6Text.text = "영화, 나의 슬픔"
            binding.level7Text.text = "영화, 나의 안식"
            binding.level8Text.text = "영화, 나의 구원"
            binding.level9Text.text = "영화, 나"
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentThree.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentThree().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}