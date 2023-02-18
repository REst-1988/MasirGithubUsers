package com.example.masirgithubusers.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.masirgithubusers.R
import com.example.masirgithubusers.adapters.FollowAdapter
import com.example.masirgithubusers.adapters.MainScreenAdapter
import com.example.masirgithubusers.databinding.FragmentFollowBinding
import com.example.masirgithubusers.databinding.FragmentMainScreenBinding
import com.example.masirgithubusers.viewmodels.MainViewModel

/**
 * Followers and Following are showed in this fragment which used for both list
 */

private const val TAG = "FollowFrag"

class FollowFrag(private val frag: Int) : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentFollowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViewForFollowingAndFollowers(frag)
    }

    /***********************************************************************/
    private fun setupRecyclerViewForFollowingAndFollowers(frag: Int) {
        if (frag == 0)  // if FollowFrag is setting up for followers
            setupFollowers()
        else  // if FollowFrag is setting up for following
            setupFollowing()
    }

    private fun setupFollowers() {
        val adapter = FollowAdapter()
        binding.rvFollow.adapter = adapter
        viewModel.getFollowers(username = viewModel.gitUsername.value)
        viewModel.followers.observe(viewLifecycleOwner) {
            adapter.submitList(viewModel.followers.value)
        }
    }

    private fun setupFollowing() {
        val adapter2 = FollowAdapter()
        binding.rvFollow.adapter = adapter2
        viewModel.getFollowing(username = viewModel.gitUsername.value)
        viewModel.following.observe(viewLifecycleOwner) {
            adapter2.submitList(viewModel.following.value)
        }
    }
}