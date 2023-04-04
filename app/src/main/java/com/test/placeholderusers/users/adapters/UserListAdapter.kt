package com.test.placeholderusers.users.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.entities.User
import com.test.placeholderusers.R
import com.test.placeholderusers.databinding.UserItemBinding

class UserListAdapter(private val listener: UserListAdapterListener) :
    ListAdapter<User, UserListAdapter.ViewHolder>(UserAdapterDiffCallback()) {

    interface UserListAdapterListener {
        fun onUserDetail(user: User)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.setServiceUI(item, listener)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = UserItemBinding.bind(itemView)

        fun setServiceUI(
            user: User,
            listener: UserListAdapterListener
        ) {
            binding.name.text = user.name
            binding.phone.text = user.phone
            binding.email.text = user.email
        }
    }
}

class UserAdapterDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}

