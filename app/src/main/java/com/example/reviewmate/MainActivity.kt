package com.example.reviewmate

import android.R
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.reviewmate.databinding.ActivityMainBinding
import com.example.reviewmate.databinding.FragmentOneBinding
import com.example.reviewmate.databinding.FragmentTwoBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    class MyFragmentAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity){
        val fragments : List<Fragment>
        init{
            fragments = listOf(FragmentOne(), FragmentTwo(), FragmentThree(), FragmentFour(), FragmentFive() )
        }

        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val binding2 = FragmentOneBinding.inflate(layoutInflater)
        val binding3 = FragmentTwoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }


}

