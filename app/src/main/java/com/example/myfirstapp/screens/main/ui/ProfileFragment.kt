package com.example.myfirstapp.screens.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myfirstapp.databinding.FragmentProfileBinding
import org.koin.android.ext.android.inject

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val userViewModel by inject<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

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

        observerUserData()
        userViewModel.getUsername(1)
    }

    private fun handleUpdateClick() {
        val name = binding.edtName.text.toString().trim()

        if (name.isBlank()) {
            return
        }

        val updateUser = userViewModel.userLiveData.value?.copy(name = name)

        updateUser?.let {
            userViewModel.updateUser(updateUser)
        }
    }

    private fun observerUserData() {
        userViewModel.userLiveData.observe(viewLifecycleOwner) { user ->
            user?.let {
                binding.tvName.text = user.name
                binding.edtName.setText("")
            }
        }
    }
}