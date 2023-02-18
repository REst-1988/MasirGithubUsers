package com.example.masirgithubusers.model

import com.squareup.moshi.Json

data class SearchModel (
    @Json(name = "total_count") val totalCount: Int,
    @Json(name = "incomplete_results") val incompleteResults: Boolean,
    @Json(name = "items") val items: List<GitUser>
)