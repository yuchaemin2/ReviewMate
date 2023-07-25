package com.example.reviewmate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.reviewmate.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Home page"

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav1 -> {
                    loadFragment(FragmentOne())
                    supportActionBar?.title = "Home page"
                }
                R.id.nav2 -> {
                    loadFragment(FragmentTwo())
                    supportActionBar?.title = "Search"
                }
                R.id.nav3 -> {
                    loadFragment(FragmentThree())
                    supportActionBar?.title = "Character"
                }
                R.id.nav4 -> {
                    loadFragment(FragmentFour())
                    supportActionBar?.title = "Review"
                }
                R.id.nav5 -> {
                    loadFragment(FragmentFive())
                    supportActionBar?.title = "My page"
                }
            }
            true
        }

        // Set the default fragment to load when the activity is created
        loadFragment(FragmentOne())
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_layout, fragment)
        transaction.addToBackStack(null) // Optional: Add the fragment to the back stack
        transaction.commit()
    }
}
