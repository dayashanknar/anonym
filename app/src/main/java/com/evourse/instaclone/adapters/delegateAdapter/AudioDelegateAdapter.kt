package com.evourse.NovaRaze.ui.adapters.delegateAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.evourse.instaclone.R
import com.evourse.instaclone.convertNumberToString
import com.evourse.instaclone.convertToTimeAgo
import com.evourse.instaclone.databinding.LayAudioPostsBinding
import com.evourse.instaclone.model.Feed

class AudioDelegateAdapter : DelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayAudioPostsBinding.inflate(inflater, parent, false)
        return AudioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, feed: Feed) {
        if (holder is AudioViewHolder) {
            holder.bind(feed)
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder is AudioViewHolder) {
            // Reset the ImageView's image resource or any other associated properties
            holder.resetAudioView()
        }
    }

    inner class AudioViewHolder(private val binding: LayAudioPostsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(feed: Feed) {
            // Bind video data to views using the binding
            Glide.with(binding.root.context)
                .load(feed.publisher.profilePictureUrl) // Use imgPost.imgUrl instead of ImagePostsDtc.imgUrl
                .placeholder(R.drawable.ic_refresh) // Placeholder image while loading
                .error(R.drawable.ic_add) // Image to display in case of an error
                .into(binding.profileImgFd)

            binding.profileNameFd.text = feed.publisher.fullName

            binding.audPubDate.text = convertToTimeAgo(feed.postDate)

            binding.audTitle.text = feed.captionText
            binding.audViewCount.text = convertNumberToString(feed.impressionCount)

        }

        fun resetAudioView() {
            Glide.with(binding.profileImgFd)
                .clear(binding.profileImgFd)
        }

        // Reset other views to default values}
    }
}
