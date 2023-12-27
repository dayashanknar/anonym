package com.evourse.instaclone

import com.evourse.instaclone.model.FeedResponseApi
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeFeedApis {
    @GET("/venCon")
    suspend fun getMixContent(@Query("page") page: Int): FeedResponseApi

}