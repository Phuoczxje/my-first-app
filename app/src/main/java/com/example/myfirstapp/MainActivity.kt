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
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : FragmentActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private val fragmentMap = mapOf(
        "Profile" to ProfileFragment(),
        "Friends" to FriendsFragment(),
        "Notifications" to NotificationsFragment(),
        "Settings" to SettingsFragment()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewPager = binding.pagerMain
        tabLayout = binding.tabLayout

        val pagerAdapter = MainPagerAdapter(this)
        viewPager.adapter = pagerAdapter

        setupTabLayoutWithPager()
        handleOnBackPressed()
    }

    private fun setupTabLayoutWithPager() {
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = fragmentMap.keys.elementAt(position)
        }.attach()
    }

    private fun handleOnBackPressed() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

            }
        })
    }

    private inner class MainPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = fragmentMap.size

        override fun createFragment(position: Int): Fragment {
            return fragmentMap.values.elementAt(position)
        }
    }
}


