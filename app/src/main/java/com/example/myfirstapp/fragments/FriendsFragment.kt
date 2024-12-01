package com.example.myfirstapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirstapp.adapters.UserAdapter
import com.example.myfirstapp.databinding.FragmentFriendsBinding
import com.example.myfirstapp.models.User
import com.example.myfirstapp.viewmodels.UserViewModel
import org.koin.android.ext.android.inject

class FriendsFragment : Fragment() {
    private lateinit var binding: FragmentFriendsBinding
    private lateinit var userAdapter: UserAdapter

    private val userViewModel: UserViewModel by inject()
    private var users: List<User> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFriendsBinding.inflate(inflater, container, false)
        userAdapter = UserAdapter(users)

        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rcvMain.layoutManager = LinearLayoutManager(this.root.context)
            rcvMain.adapter = userAdapter
            rcvMain.addItemDecoration(
                DividerItemDecoration(rcvMain.context, DividerItemDecoration.VERTICAL)
            )
        }

        observerUsersData()
        userViewModel.getUsers()
    }

    private fun observerUsersData() {
        userViewModel.userListLiveData.observe(viewLifecycleOwner, Observer { userList ->
            userList?.let {
                users = userList
                userAdapter.updateUsers(users)
            }
        })
    }
}