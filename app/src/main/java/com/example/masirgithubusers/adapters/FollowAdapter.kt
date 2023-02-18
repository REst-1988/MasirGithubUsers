package com.example.masirgithubusers.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.masirgithubusers.databinding.ItemMainBinding
import com.example.masirgithubusers.model.GitUser

class FollowAdapter:
ListAdapter<GitUser, FollowAdapter.GitUserViewHolder>(DiffUtilCallBack) {

    // Diff call back does not used in this project yet but it would be handy for future changes
    companion object DiffUtilCallBack : DiffUtil.ItemCallback<GitUser>() {
        override fun areItemsTheSame(oldItem: GitUser, newItem: GitUser): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GitUser, newItem: GitUser): Boolean {
            return oldItem.avatarUrl == newItem.avatarUrl
        }
    }

    class GitUserViewHolder(private var binding: ItemMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(gitUser: GitUser) {
            binding.user = gitUser
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitUserViewHolder {
        return GitUserViewHolder(
            ItemMainBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: GitUserViewHolder, position: Int) {
        val gitUser = getItem(position)
        holder.bind(gitUser)
    }
}