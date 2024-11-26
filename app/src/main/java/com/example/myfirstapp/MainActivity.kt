package com.example.myfirstapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myfirstapp.fragments.FriendsFragment
import com.example.myfirstapp.fragments.NotificationsFragment
import com.example.myfirstapp.fragments.ProfileFragment
import com.example.myfirstapp.fragments.SettingsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbar)
        replaceFragment(ProfileFragment())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuProfile -> {
                replaceFragment(ProfileFragment())
                true
            }

            R.id.menuFriends -> {
                replaceFragment(FriendsFragment())
                true
            }

            R.id.menuNotifications -> {
                replaceFragment(NotificationsFragment())
                true
            }

            R.id.menuSettings -> {
                replaceFragment(SettingsFragment())
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                fragment
            )
            .commit()
    }

}