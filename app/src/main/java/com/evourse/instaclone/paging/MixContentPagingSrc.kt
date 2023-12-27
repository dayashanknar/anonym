package com.evourse.instaclone.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.evourse.instaclone.HomeFeedApis
import com.evourse.instaclone.model.Feed

class MixContentPagingSrc(private val homeFeedApi: HomeFeedApis) :
    PagingSource<Int, Feed>() {
    override fun getRefreshKey(state: PagingState<Int, Feed>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Feed> {
        return try {
            val page = params.key ?: 1 // If key is null, start from the first page
            val response = homeFeedApi.getMixContent(page) // Use getTextPosts for text posts

            val nextPage = response.takeIf { page < it.totalPages }?.let { page + 1 }
            val prevPage = if (page == 1) null else page - 1
            LoadResult.Page(
                data = response.feed,
                prevKey = prevPage,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}