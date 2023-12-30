package com.evourse.NovaRaze.ui.adapters.delegateAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.evourse.instaclone.R
import com.evourse.instaclone.convertNumberToString
import com.evourse.instaclone.convertToTimeAgo
import com.evourse.instaclone.databinding.LayTextPostBinding
import com.evourse.instaclone.model.Feed

class TextDelegateAdapter : DelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayTextPostBinding.inflate(inflater, parent, false)
        return TextViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, feed: Feed) {
        if (holder is TextViewHolder) {
            holder.bind(feed)
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder is TextViewHolder) {
            // Reset the ImageView's image resource or any other associated properties
            holder.resetTextView()
        }
    }

    inner class TextViewHolder(private val binding: LayTextPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(feed: Feed) {
            Glide.with(binding.root.context)
                .load(feed.publisher.profilePictureUrl) // Use imgPost.imgUrl instead of ImagePostsDtc.imgUrl
                .placeholder(R.drawable.ic_refresh) // Placeholder image while loading
                .error(R.drawable.ic_add) // Image to display in case of an error
                .into(binding.profileImgFd)

            binding.profileNameFd.text = feed.publisher.fullName

            binding.textPostTitle.text = feed.captionText
            binding.textPostLikesCountFd.text = convertNumberToString(feed.likeCount)
            binding.textPostCommentCountFd.text = convertNumberToString(feed.commentCount)
            binding.textScoreFd.text =
                binding.textScoreCFd.context.getString(R.string.gScore, feed.postScore)
            binding.viewsCount.text = convertNumberToString(feed.impressionCount)
            binding.textPubDate.text = convertToTimeAgo(feed.postDate)

        }

        fun resetTextView() {
            // Clear Glide resources for profile image
            Glide.with(binding.profileImgFd)
                .clear(binding.profileImgFd)

            // Reset other views to default values
            binding.profileNameFd.text = ""
            binding.textPostTitle.text = ""
            binding.textPostLikesCountFd.text = "0"
            binding.textPostCommentCountFd.text = "0"
            binding.textScoreFd.text = ""
            binding.viewsCount.text = "0"
            binding.textPubDate.text = ""
        }

    }
}
