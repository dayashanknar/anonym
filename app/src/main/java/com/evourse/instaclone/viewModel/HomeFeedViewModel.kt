package com.evourse.instaclone.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.evourse.instaclone.repository.HomeFeedRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeFeedViewModel @Inject constructor(
    repository: HomeFeedRepo
) : ViewModel() {

    val mixContentList = repository.getMixContent().cachedIn(viewModelScope)
}