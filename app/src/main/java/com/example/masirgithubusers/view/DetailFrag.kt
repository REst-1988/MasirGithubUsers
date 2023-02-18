package com.example.masirgithubusers.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.masirgithubusers.adapters.ViewPagerAdapter
import com.example.masirgithubusers.databinding.FragmentDetailBinding
import com.example.masirgithubusers.viewmodels.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator

class DetailFrag : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        setupTabLayoutAndPagerLayout()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserDetail(viewModel.gitUsername.value)
    }

    /**********************************************************************************/
    private fun setupTabLayoutAndPagerLayout() {
        val adapter = ViewPagerAdapter(requireActivity())
        // in order to use one fragment for bout following situations numbers added
        adapter.addFragment(FollowFrag(0), "FOLLOWERS")
        adapter.addFragment(FollowFrag(1), "FOLLOWING")
        binding.viewPager.adapter = adapter
        binding.viewPager.currentItem = 0
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = adapter.getTabTitle(position)
        }.attach()
    }
}