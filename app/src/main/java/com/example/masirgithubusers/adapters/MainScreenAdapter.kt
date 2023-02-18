package com.example.masirgithubusers.adapters

import android.graphics.Path.Direction
import android.text.Layout.Directions
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.masirgithubusers.R
import com.example.masirgithubusers.databinding.ItemMainBinding
import com.example.masirgithubusers.model.GitUser
import com.example.masirgithubusers.viewmodels.MainViewModel

class MainScreenAdapter (val viewModel: MainViewModel):
    ListAdapter<GitUser, MainScreenAdapter.GitUserViewHolder>(DiffUtilCallBack) {

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
        holder.itemView.setOnClickListener {
            viewModel.setGitUserValue(gitUser.username)
            holder.itemView.findNavController().navigate(R.id.action_mainScreenFrag_to_detailFrag)
        }
    }
}


