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
import androidx.fragment.app.FragmentTransaction
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
        val view = inflater.inflate(R.layout.fragment_five, container, false)

        // button 클릭시 fragmenrFive_ReviwList로 이동
        var btn_move: Button = view.findViewById(R.id.btn_move)
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
        val logoutButton = view.findViewById<Button>(R.id.logoutButton)
        logoutButton.setOnClickListener {
            logout()
        }




        return view
    }


//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        // 로그아웃 버튼을 레이아웃에서 찾아서 클릭 리스너를 추가
//        val reviewListBtn = view.findViewById<Button>(R.id.btnGoList)
//        reviewListBtn.setOnClickListener {
//                    }
//    }

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
    private fun loadLayout(){

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