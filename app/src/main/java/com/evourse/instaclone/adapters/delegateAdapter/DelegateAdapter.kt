package com.evourse.NovaRaze.ui.adapters.delegateAdapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evourse.instaclone.model.Feed


interface DelegateAdapter {
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    fun onBindViewHolder(holder: RecyclerView.ViewHolder, feed: Feed)
    fun onViewRecycled(holder: RecyclerView.ViewHolder)
}