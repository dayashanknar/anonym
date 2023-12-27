package com.evourse.instaclone.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.evourse.instaclone.HomeFeedApis
import com.evourse.instaclone.paging.MixContentPagingSrc
import javax.inject.Inject

class HomeFeedRepo @Inject constructor(private val homeFeedApis: HomeFeedApis) {
    fun getMixContent() = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 20, maxSize = 60),
        pagingSourceFactory = { MixContentPagingSrc(homeFeedApis) }
    ).liveData
}