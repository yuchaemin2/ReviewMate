package com.example.reviewmate

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reviewmate.databinding.ActivityAddBinding
import com.example.reviewmate.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Home page"

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav1 -> {
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
            false
        }

        // Set the default fragment to load when the activity is created
        loadFragment(FragmentOne())
        bottomNavigationView.selectedItemId = R.id.nav1
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