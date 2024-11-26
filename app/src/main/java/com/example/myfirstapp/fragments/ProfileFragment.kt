package com.example.myfirstapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.myfirstapp.ProductsActivity
import com.example.myfirstapp.R
import com.example.myfirstapp.directToOtherActivity

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val profileButton: Button = view.findViewById(R.id.buttonProfile)

        profileButton.setOnClickListener {
            handleProfileClick(requireContext())
        }
    }

    private fun handleProfileClick(context: Context) {
        context.directToOtherActivity(ProductsActivity::class.java)
    }
}