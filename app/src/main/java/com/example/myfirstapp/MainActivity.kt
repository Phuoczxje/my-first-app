package com.example.myfirstapp

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.myfirstapp.databinding.ActivityMainBinding
import com.example.myfirstapp.fragments.FriendsFragment
import com.example.myfirstapp.fragments.NotificationsFragment
import com.example.myfirstapp.fragments.ProfileFragment
import com.example.myfirstapp.fragments.SettingsFragment

private const val NUM_PAGES = 4

class MainActivity : FragmentActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewPager = binding.pagerMain

        val pagerAdapter = MainPagerAdapter(this)
        viewPager.adapter = pagerAdapter

        handleOnBackPressed()
    }

    private inner class MainPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                1 -> ProfileFragment()
                2 -> FriendsFragment()
                3 -> NotificationsFragment()
                4 -> SettingsFragment()
                else -> ProfileFragment()
            }
        }
    }

    private fun handleOnBackPressed() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (viewPager.currentItem == 0) {
                    finish()
                } else {
                    viewPager.currentItem -= 1
                }
            }
        })
    }
}


