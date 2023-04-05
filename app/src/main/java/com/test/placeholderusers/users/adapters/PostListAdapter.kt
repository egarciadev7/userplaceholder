package com.test.placeholderusers.users.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.entities.Post
import com.test.placeholderusers.R
import com.test.placeholderusers.databinding.PostItemBinding

class PostListAdapter :
    ListAdapter<Post, PostListAdapter.ViewHolder>(PostAdapterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.setPostUi(item)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = PostItemBinding.bind(itemView)

        fun setPostUi(
            post: Post,
        ) {
            binding.tvPostsTitle.text = post.title
            binding.tvPostBody.text = post.body
        }
    }
}

class PostAdapterDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}



