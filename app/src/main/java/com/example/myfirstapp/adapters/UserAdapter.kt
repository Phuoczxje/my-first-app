package com.example.myfirstapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.databinding.UserItemBinding
import com.example.myfirstapp.models.User

class UserAdapter(private var items: List<User>) :
    RecyclerView.Adapter<UserAdapter.ItemViewHolder>() {
    private lateinit var binding: UserItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val user = items[position]
        holder.binItem(user)
    }

    override fun getItemCount(): Int = items.size

    fun updateUsers(newUsers: List<User>) {
        this.items = newUsers
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun binItem(item: User) {
            binding.apply {
                itemName.text = item.name
                itemEmail.text = item.email
            }
        }
    }
}