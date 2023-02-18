package com.example.masirgithubusers

import com.example.masirgithubusers.viewmodels.MainViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    lateinit var viewModel: MainViewModel
    @Before
    fun setUp(){
        viewModel = MainViewModel()
    }
}