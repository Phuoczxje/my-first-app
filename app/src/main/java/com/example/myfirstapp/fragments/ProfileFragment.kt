package com.example.myfirstapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.myfirstapp.databinding.FragmentProfileBinding
import com.example.myfirstapp.viewmodels.UserViewModel
import org.koin.android.ext.android.inject

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val userViewModel: UserViewModel by inject()

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
            userViewModel.updateUser(1, updateUser)
        }
    }

    private fun observerUserData() {
        userViewModel.userLiveData.observe(viewLifecycleOwner, Observer { user ->
            user?.let {
                binding.tvName.text = user.name
                binding.edtName.setText("")
            }
        })
    }
}