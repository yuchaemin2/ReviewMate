package com.example.reviewmate

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.reviewmate.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_main)

        // 채민



    }

    override fun onStart() {
        super.onStart()
        // 사용자가 이미 로그인되어 있는지 확인
        if(MyApplication.checkAuth()){
            // 로그인 시 -> menu보여줌
            // Set the default fragment to load when the activity is created
            loadFragment(FragmentOne())

            bottomNavigationView = findViewById(R.id.bottomNavigationView)
            bottomNavigationView.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav1 -> loadFragment(FragmentOne())
                    R.id.nav2 -> loadFragment(FragmentTwo())
                    R.id.nav3 -> loadFragment(FragmentThree())
                    R.id.nav4 -> loadFragment(FragmentFour())
                    R.id.nav5 -> loadFragment(FragmentFive())
                }
                true

            }
        }else { // 로그인이 안되어있을때 -> 로그인 화면으로 이동
            //binding.logoutTextView.visibility= View.GONE
            //binding.mainRecyclerView.visibility=View.VISIBLE
            Toast.makeText(baseContext, "로그인창으로 이동합니다", Toast.LENGTH_SHORT).show()
            // 로그인 화면으로 이동하는 인텐트 생성 및 실행
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            //finish() // 현재 MainActivity 종료 (선택사항)
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_layout, fragment)
        transaction.addToBackStack(null) // Optional: Add the fragment to the back stack
        transaction.commit()
    }

}
