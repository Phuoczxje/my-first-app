package com.example.myfirstapp.screens.main.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirstapp.databinding.FragmentFriendsBinding
import com.example.myfirstapp.screens.main.data.models.User
import com.example.myfirstapp.screens.main.ui.adapter.UserAdapter
import org.koin.android.ext.android.inject

class FriendsFragment : Fragment() {
    private lateinit var binding: FragmentFriendsBinding

    private var users: MutableList<User> = mutableListOf()
    private val userAdapter by lazy(LazyThreadSafetyMode.NONE) { UserAdapter(users) }
    private val userViewModel by inject<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFriendsBinding.inflate(inflater, container, false)

        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rcvMain.setHasFixedSize(false)
            rcvMain.layoutManager = LinearLayoutManager(this.root.context)
            rcvMain.adapter = userAdapter
        }

        userViewModel.getUsers()
        observerUsersData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observerUsersData() {
        userViewModel.usersLiveData.observe(viewLifecycleOwner) { userList ->
            users.apply {
                clear()
                addAll(userList)
            }
            userAdapter.notifyDataSetChanged()
        }
    }
}