package com.evourse.instaclone.model

data class Feed(
    val captionText: String,
    val commentCount: Int,
    val contentUrl: String? = null,
    val impressionCount: Int,
    val likeCount: Int,
    val location: String,
    val postDate: String,
    val postId: String,
    val postPrivacy: String,
    val postScore: Int,
    val postType: String,
    val publisher: Publisher
)