package com.example.myfirstapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.myfirstapp.ProductsActivity
import com.example.myfirstapp.R

class FriendsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_friends, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val friendsButton: Button = view.findViewById(R.id.btnFriends)

        friendsButton.setOnClickListener {
            handleFriendsClick()
        }
    }

    private fun handleFriendsClick() {
        val intent = Intent(requireContext(), ProductsActivity::class.java)
        startActivity(intent)
    }
}