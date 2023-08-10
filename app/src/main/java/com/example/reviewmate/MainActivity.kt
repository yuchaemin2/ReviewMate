package com.example.reviewmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.reviewmate.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    lateinit var binding: ActivityMainBinding
    var html = "http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/getNearbyMsrstnList?tmX=244148.546388&tmY=412423.75772&pageNo=1&numOfRows=10&ServiceKey=서비스키"


    companion object {
        const val MOVIE_POSTER = "extra_movie_poster"
        const val MOVIE_TITLE = "extra_movie_title"
        const val MOVIE_RATING = "extra_movie_rating"
        const val MOVIE_OVERVIEW = "extra_movie_overview"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Home page"

        if(MyApplication.checkAuth()){
            bottomNavigationView = findViewById(R.id.bottomNavigationView)
            bottomNavigationView.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav1 -> {
                        updateIcons(item, R.drawable.home_1)
                        loadFragment(FragmentOne())
                        supportActionBar?.title = "Home page"
                    }
                    R.id.nav2 -> {
                        loadFragment(FragmentTwo())
                        supportActionBar?.title = "Search"
                        updateIcons(item, R.drawable.search_1)
                    }
                    R.id.nav3 -> {
                        loadFragment(FragmentThree())
                        supportActionBar?.title = "Character"
                        updateIcons(item, R.drawable.paw_1)
                    }
                    R.id.nav4 -> {
                        loadFragment(FragmentFour())
                        supportActionBar?.title = "Review"
                        updateIcons(item, R.drawable.satisfaction_1)
                    }
                    R.id.nav5 -> {
                        loadFragment(FragmentFive())
                        supportActionBar?.title = "My page"
                        updateIcons(item, R.drawable.user_1)
                    }
                }
                true
            }
            // Set the default fragment to load when the activity is created
            loadFragment(FragmentOne())
            bottomNavigationView.selectedItemId = R.id.nav1
        }
        else {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        // Intent에서 finish() 돌아올 때 실행
        // onCreate -> onStart
        super.onStart()

        if(MyApplication.checkAuth()){

//            Toast.makeText(baseContext, "로그인 성공", Toast.LENGTH_SHORT).show()

            bottomNavigationView = findViewById(R.id.bottomNavigationView)
            bottomNavigationView.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav1 -> {
                        updateIcons(item, R.drawable.home_1)
                        loadFragment(FragmentOne())
                        supportActionBar?.title = "Home page"
                    }
                    R.id.nav2 -> {
                        loadFragment(FragmentTwo())
                        supportActionBar?.title = "Search"
                        updateIcons(item, R.drawable.search_1)
                    }
                    R.id.nav3 -> {
                        loadFragment(FragmentThree())
                        supportActionBar?.title = "Character"
                        updateIcons(item, R.drawable.paw_1)
                    }
                    R.id.nav4 -> {
                        loadFragment(FragmentFour())
                        supportActionBar?.title = "Review"
                        updateIcons(item, R.drawable.satisfaction_1)
                    }
                    R.id.nav5 -> {
                        loadFragment(FragmentFive())
                        supportActionBar?.title = "My page"
                        updateIcons(item, R.drawable.user_1)
                    }
                }
                true
            }
        }
        else {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
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
    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_layout, fragment)
        transaction.addToBackStack(null) // Optional: Add the fragment to the back stack
        transaction.commit()
    }
}
