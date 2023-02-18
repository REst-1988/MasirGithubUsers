package com.example.masirgithubusers.network.interfaces

import com.example.masirgithubusers.model.GitUser
import com.example.masirgithubusers.model.SearchModel
import com.example.masirgithubusers.model.UserModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


private const val GIT_INFINITE_TOKEN = "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"

interface GitApiService {
    @GET("users")
    @Headers(
        "Accept: application/vnd.github+json",
        "Authorization: Bearer $GIT_INFINITE_TOKEN",
        "X-GitHub-Api-Version: 2022-11-28")
    suspend fun getUserList(): List<GitUser>

    @GET("search/users")
    @Headers(
        "Accept: application/vnd.github+json",
        "Authorization: Bearer $GIT_INFINITE_TOKEN",
        "X-GitHub-Api-Version: 2022-11-28")
    suspend fun getSearchUsers(
        @Query("q") query: String
    ): SearchModel

    @GET("users/{username}")
    @Headers(
        "Accept: application/vnd.github+json",
        "Authorization: Bearer $GIT_INFINITE_TOKEN",
        "X-GitHub-Api-Version: 2022-11-28")
    suspend fun getUser(
        @Path(value = "username") username: String
    ): UserModel

    @GET("users/{username}/followers")
    @Headers(
        "Accept: application/vnd.github+json",
        "Authorization: Bearer $GIT_INFINITE_TOKEN",
        "X-GitHub-Api-Version: 2022-11-28")
    suspend fun getFollower(
        @Path(value = "username") username: String
    ): List<GitUser>

    @GET("users/{username}/following")
    @Headers(
        "Accept: application/vnd.github+json",
        "Authorization: Bearer $GIT_INFINITE_TOKEN",
        "X-GitHub-Api-Version: 2022-11-28")
    suspend fun getFollowing(
        @Path(value = "username") username: String
    ): List<GitUser>
}
