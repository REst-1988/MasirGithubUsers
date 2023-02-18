package com.example.masirgithubusers.network

import com.example.masirgithubusers.network.interfaces.GitApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL =
    "https://api.github.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


object GitApi {
    val retrofitService: GitApiService by lazy {
        retrofit.create(GitApiService::class.java)
    }
}