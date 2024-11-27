package com.example.myfirstapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myfirstapp.databinding.FragmentProfileBinding
import com.example.myfirstapp.viewmodels.UserViewModel

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        userViewModel = ViewModelProvider(this@ProfileFragment)[UserViewModel::class.java]

        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnUpdate.setOnClickListener {
                handleUpdateClick()
            }
        }

        userViewModel.getUserDetail("1")
        observerUserData()
    }

    private fun handleUpdateClick() {
        userViewModel.
    }

    /**
     *
    private fun handleProfileClick() {
    val intent = Intent(context, ProductsActivity::class.java)
    startActivity(intent)
    }
     */

    private fun observerUserData() {
        userViewModel.userData.observe(viewLifecycleOwner, Observer { value ->
            binding.tvName.text
        })
    }
}