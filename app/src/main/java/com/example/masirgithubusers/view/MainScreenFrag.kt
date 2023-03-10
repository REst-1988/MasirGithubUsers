package com.example.masirgithubusers.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import com.example.masirgithubusers.adapters.MainScreenAdapter
import com.example.masirgithubusers.databinding.FragmentMainScreenBinding
import com.example.masirgithubusers.viewmodels.MainViewModel

class MainScreenFrag : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    lateinit var binding: FragmentMainScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainScreenBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.rvMainScreen.adapter = MainScreenAdapter(viewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // searching users using git api
        binding.searchViewHome.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchViewHome.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.getSearchUsers(newText)
                if (newText.equals(null) || newText!!.isEmpty() || newText == ""){
                    viewModel.getGitUserList()
                }
                return false
            }

        })
    }
}