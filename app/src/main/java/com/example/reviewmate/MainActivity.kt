package com.example.reviewmate

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.example.reviewmate.databinding.ActivityMainBinding
import com.example.reviewmate.databinding.FragmentThreeBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest


class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    lateinit var binding: ActivityMainBinding
//    lateinit var binding2: FragmentThreeBinding

    lateinit var sharedPreferences: SharedPreferences
    lateinit var adapter: MyFeedAdapter

    var authMenuItem : MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding2= FragmentThreeBinding()

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

//        //add................................
//        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
//        val profileIcon: Int = sharedPreferences.getInt("profileIcon", R.drawable.user)
//        binding2.userProfile.setImageResource(profileIcon)
//
//        adapter = MyFeedAdapter(this, itemList = null)
    }

//    override fun onResume() {
//        super.onResume()
//
//        val profileIcon: Int = sharedPreferences.getInt("profileIcon", R.drawable.user)
//        binding2.userProfile.setImageResource(profileIcon)
//
////        adapter.notifyDataSetChanged()
//    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_main, menu)
//
//        authMenuItem = menu!!.findItem(R.id.menu_auth)
//        if(MyApplication.checkAuth()){
//            authMenuItem!!.title = "${MyApplication.email}님"
//        }
//        else {
//            authMenuItem!!.title = "인증"
//        }
//        return super.onCreateOptionsMenu(menu)
//    }

    override fun onStart() {
        // Intent에서 finish() 돌아올 때 실행
        // onCreate -> onStart
        super.onStart()

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if(item.itemId === R.id.menu_auth){
//            val intent = Intent(this, AuthActivity::class.java)
//            if(authMenuItem!!.title!!.equals("인증")){
//                intent.putExtra("data", "logout")
//            }
//            else { // 이메일, 구글 계정
//                intent.putExtra("data", "login")
//            }
//            startActivity(intent)
//        }
//        else if (item.itemId === R.id.menu_main_setting){
//            var intent = Intent(this, SettingActivity::class.java)
//            startActivity(intent)
//            return true
//        }
//        return super.onOptionsItemSelected(item)
//    }

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
