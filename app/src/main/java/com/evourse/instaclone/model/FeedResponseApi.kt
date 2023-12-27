package com.evourse.instaclone.model

data class FeedResponseApi(
    val currentPage: Int,
    val feed: List<Feed>,
    val lastItemIndex: Int,
    val totalEntries: Int,
    val totalPages: Int
)