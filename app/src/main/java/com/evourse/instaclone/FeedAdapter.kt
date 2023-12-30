package com.evourse.instaclone

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.evourse.NovaRaze.ui.adapters.delegateAdapter.AudioDelegateAdapter
import com.evourse.NovaRaze.ui.adapters.delegateAdapter.ImageDelegateAdapter
import com.evourse.NovaRaze.ui.adapters.delegateAdapter.TextDelegateAdapter
import com.evourse.NovaRaze.ui.adapters.delegateAdapter.VideoDelegateAdapter
import com.evourse.instaclone.databinding.LayAudioPostsBinding
import com.evourse.instaclone.databinding.LayFeedVideoBinding
import com.evourse.instaclone.databinding.LayImgFeedBinding
import com.evourse.instaclone.databinding.LayTextPostBinding
import com.evourse.instaclone.model.Feed
import com.evourse.instaclone.viewHolder.FeedViewHolder


class FeedAdapter : PagingDataAdapter<Feed, RecyclerView.ViewHolder>(FeedComparator) {

    private val delegateAdapters = mapOf(
        VIEW_TYPE_IMAGE to ImageDelegateAdapter(),
        VIEW_TYPE_VIDEO to VideoDelegateAdapter(),
        VIEW_TYPE_TEXT to TextDelegateAdapter(),
        VIEW_TYPE_AUDIO to AudioDelegateAdapter()
    )

    override fun getItemViewType(position: Int): Int {
        val feed = getItem(position)

        return when (val postType = feed?.postType) {
            "image" -> VIEW_TYPE_IMAGE
            "video", "blaze" -> VIEW_TYPE_VIDEO
            "text" -> VIEW_TYPE_TEXT
            "audio" -> VIEW_TYPE_AUDIO
            else -> {
                Log.e("FeedAdapter", "Unknown postType: $postType at position: $position")
                Log.e("FeedAdapter", feed.toString())
                // Return a default view type or handle the unknown type error appropriately
                // For example, you might return VIEW_TYPE_DEFAULT or throw an exception
                // Return VIEW_TYPE_DEFAULT
                VIEW_TYPE_TEXT
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters[viewType]?.onCreateViewHolder(parent)
            ?: throw IllegalArgumentException("Unknown view type")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val feed = getItem(position)
        feed?.let {
            val delegateAdapter = delegateAdapters[getItemViewType(position)]
            delegateAdapter?.onBindViewHolder(holder, it)
        }
    }


    companion object {
        // Constants for view types
        private const val VIEW_TYPE_IMAGE = 0
        private const val VIEW_TYPE_VIDEO = 1
        private const val VIEW_TYPE_TEXT = 2
        private const val VIEW_TYPE_AUDIO = 3
//        private const val VIEW_TYPE_UNKNOWN = -1
    }

    // COMPERATOR
    object FeedComparator : DiffUtil.ItemCallback<Feed>() {
        override fun areItemsTheSame(oldItem: Feed, newItem: Feed): Boolean {
            return oldItem.postId == newItem.postId
        }

        override fun areContentsTheSame(oldItem: Feed, newItem: Feed): Boolean {
            return oldItem == newItem
        }
    }
}
