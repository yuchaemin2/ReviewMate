package com.example.reviewmate

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
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
        loadFragment(FragmentOne())

    }

    override fun onStart() {
        super.onStart()
        // 사용자가 이미 로그인되어 있는지 확인
        if(MyApplication.checkAuth()){
            Toast.makeText(baseContext, "로그인 상태", Toast.LENGTH_SHORT).show()

            bottomNavigationView = findViewById(R.id.bottomNavigationView)
            bottomNavigationView.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {R.id.nav1 -> {
                    updateIcons(item, R.drawable.home_1)
                    loadFragment(FragmentOne())
                    supportActionBar?.title = "Home page"
                    return@setOnNavigationItemSelectedListener true
                }
                    R.id.nav2 -> {
                        loadFragment(FragmentTwo())
                        supportActionBar?.title = "Search"
                        updateIcons(item, R.drawable.search_1)
                        return@setOnNavigationItemSelectedListener true
                    }
                    R.id.nav3 -> {
                        loadFragment(FragmentThree())
                        supportActionBar?.title = "Character"
                        updateIcons(item, R.drawable.paw_1)
                        return@setOnNavigationItemSelectedListener true
                    }
                    R.id.nav4 -> {
                        loadFragment(FragmentFour())
                        supportActionBar?.title = "Review"
                        updateIcons(item, R.drawable.satisfaction_1)
                        return@setOnNavigationItemSelectedListener true
                    }
                    R.id.nav5 -> {
                        loadFragment(FragmentFive())
                        supportActionBar?.title = "My page"
                        updateIcons(item, R.drawable.user_1)

                        return@setOnNavigationItemSelectedListener true
                    }
                }
                true

            }
            //!!!!!!!!!!이거 확인하기
            bottomNavigationView.selectedItemId = R.id.nav1
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
    private fun updateIcons(selectedItem: MenuItem, selectedIconRes: Int) {
        // 선택된 항목의 아이콘을 선택된 아이콘으로 변경합니다.
        selectedItem.setIcon(selectedIconRes)

        // 다른 항목들의 아이콘을 기본 아이콘으로 되돌립니다.
        val menu = bottomNavigationView.menu
        for (i in 0 until menu.size()) {
            val item = menu.getItem(i)
            if (item != selectedItem) {
                when (item.itemId) {
                    R.id.nav1 -> item.setIcon(R.drawable.home)
                    R.id.nav2 -> item.setIcon(R.drawable.search)
                    R.id.nav3 -> item.setIcon(R.drawable.paw)
                    R.id.nav4 -> item.setIcon(R.drawable.satisfaction)
                    R.id.nav5 -> item.setIcon(R.drawable.user)
                }
            }
        }
    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_layout, fragment)
        transaction.addToBackStack(null) // Optional: Add the fragment to the back stack
        transaction.commit()
    }


}
