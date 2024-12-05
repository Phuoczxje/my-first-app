package com.example.myfirstapp.screens.main.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myfirstapp.databinding.FragmentNotificationsBinding
import com.example.myfirstapp.screens.products.ui.ProductsActivity

class NotificationsFragment : Fragment() {
    private lateinit var binding: FragmentNotificationsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)

        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnNotifications.setOnClickListener {
                handleNotificationsClick()
            }
        }
    }

    private fun handleNotificationsClick() {
        val intent = Intent(context, ProductsActivity::class.java)
        startActivity(intent)
    }
}