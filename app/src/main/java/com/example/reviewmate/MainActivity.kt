package com.example.reviewmate

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.example.reviewmate.MyApplication.Companion.auth
import com.example.reviewmate.MyApplication.Companion.db
import com.example.reviewmate.databinding.ActivityMainBinding
import com.example.reviewmate.databinding.FragmentThreeBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    lateinit var binding: ActivityMainBinding

    companion object {
        const val MOVIE_BACKDROP = "extra_movie_backdrop"
        const val MOVIE_ID = "extra_movie_id"
        const val MOVIE_POSTER = "extra_movie_poster"
        const val MOVIE_TITLE = "extra_movie_title"
        const val MOVIE_RATING = "extra_movie_rating"
        const val MOVIE_RELEASE_DATE = "extra_movie_release_date"
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

        MyApplication.db.collection("users").document(auth.uid.toString())
            .get()
            .addOnSuccessListener {  }
    }

    override fun onStart() {
        // Intent에서 finish() 돌아올 때 실행
        // onCreate -> onStart
        super.onStart()

        updateUserLevelBasedOnReviewCount(auth.uid.toString())

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

    private fun updateUserLevelBasedOnReviewCount(userId: String) {
        val userRef = db.collection("users").document(userId)

        userRef.get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val userReviewCount = documentSnapshot.getLong("userReviewCount") ?: 0
                    val newUserLevel = calculateUserLevel(userReviewCount)

                    // userLevel 업데이트
                    userRef.update("userLevel", newUserLevel)
                        .addOnSuccessListener {
                            // 업데이트 성공 시 처리
                        }
                        .addOnFailureListener { e ->
                            // 업데이트 실패 시 처리
                        }
                }
            }
            .addOnFailureListener { e ->
                // 문서 가져오기 실패 시 처리
            }
    }

    private fun calculateUserLevel(userReviewCount: Long): String {
        val zero: Long = 0
        if(userReviewCount % 10 == zero && userReviewCount != zero) {
            Toast.makeText(this, "사용자의 레벨이 올랐습니다!", Toast.LENGTH_SHORT).show()
        }
        return when {
            userReviewCount < 10 -> "1"
            userReviewCount < 20 -> "2"
            userReviewCount < 30 -> "3"
            userReviewCount < 40 -> "4"
            userReviewCount < 50 -> "5"
            userReviewCount < 60 -> "6"
            userReviewCount < 70 -> "7"
            userReviewCount < 80 -> "8"
            userReviewCount < 90 -> "9"
            else -> "1"
        }
    }
}
