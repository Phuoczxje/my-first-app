package com.example.myfirstapp.screens.main.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myfirstapp.R
import com.example.myfirstapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : FragmentActivity() {
    private lateinit var binding: ActivityMainBinding
    private val fragList = listOf(
        ProfileFragment(),
        FriendsFragment(),
        NotificationsFragment(),
        SettingsFragment()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.apply {
            val pagerAdapter = MainPagerAdapter(this@MainActivity)
            vpMain.adapter = pagerAdapter
        }

        val view = binding.root
        setContentView(view)

        setupTabLayoutWithPager()
    }

    private fun setupTabLayoutWithPager() = binding.run {
        TabLayoutMediator(tlMain, vpMain) { tab, position ->
            when (position) {
                0 -> tab.setIcon(R.drawable.ic_profile)
                1 -> tab.setIcon(R.drawable.ic_friends)
                2 -> tab.setIcon(R.drawable.ic_notifications)
                3 -> tab.setIcon(R.drawable.ic_settings)
            }
        }.attach()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private inner class MainPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = fragList.size

        override fun createFragment(position: Int): Fragment {
            return fragList[position]
        }
    }
}


