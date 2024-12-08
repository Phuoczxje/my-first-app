package com.example.myfirstapp.screens.main.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.databinding.UserItemBinding
import com.example.myfirstapp.screens.main.data.models.User

class UserAdapter(private var items: List<User>) :
    RecyclerView.Adapter<UserAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val binding =
            UserItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binItem(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ItemViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binItem(item: User) {
            binding.apply {
                tvName.text = item.name
                tvEmail.text = item.email
            }
        }
    }
}