package com.example.masirgithubusers.model

import com.squareup.moshi.Json

data class UserModel(
    @Json(name = "login") val username: String,
    @Json(name = "id") val id: Int,
    @Json(name = "avatar_url") val avatarUrl: String,
    @Json(name = "name") val name: String,
    @Json(name = "followers_url") val followersUrl: String,
    @Json(name = "following_url") val followingUrl: String,
    @Json(name = "public_repos") val publicRepos: Int,
    @Json(name = "followers") val followers: Int,
    @Json(name = "following") val following: Int
)
