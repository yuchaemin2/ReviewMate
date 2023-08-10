package com.example.reviewmate

import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.reviewmate.MyApplication.Companion.auth
import com.example.reviewmate.MyApplication.Companion.db
import com.example.reviewmate.databinding.FragmentFiveBinding
import com.example.reviewmate.databinding.FragmentFourBinding
import com.google.firebase.auth.FirebaseAuth

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

        fetchUserLevel()

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

        // 로그아웃 버튼을 레이아웃에서 찾아서 클릭 리스너를 추가
        binding.logoutButton.setOnClickListener {
            logout()
        }

        if(MyApplication.checkAuth()){
            binding.CertifyEmailView.text = "${MyApplication.email}"
        }

        return binding.root
    }

//    override fun onStart() {
//        super.onStart()
//        fetchUserLevel()
//    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = childFragmentManager.beginTransaction() // fragment-fragment는 chid
        transaction.replace(R.id.five_layout, fragment)
        transaction.addToBackStack(null) // Optional: Add the fragment to the back stack
        transaction.commit()
    }

    private fun logout() {
        val intent = Intent(requireContext(), AuthActivity::class.java)
        startActivity(intent)
    }

    private fun fetchUserLevel() {
        val currentUser = auth.currentUser

        currentUser?.let {
            val userId = currentUser.uid

            val userRef = db.collection("users").document(userId)
            userRef.get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val userLevel = documentSnapshot.getString("userLevel")
                        binding.userLevelTextView.text = "Level.${userLevel}"
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(requireContext(), "사용자의 레벨을 가져오지 못했습니다.", Toast.LENGTH_SHORT).show()
                }
        }
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