package com.evourse.NovaRaze.ui.adapters.delegateAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.evourse.instaclone.R
import com.evourse.instaclone.convertNumberToString
import com.evourse.instaclone.convertToTimeAgo
import com.evourse.instaclone.databinding.LayImgFeedBinding
import com.evourse.instaclone.model.Feed

class ImageDelegateAdapter : DelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayImgFeedBinding.inflate(inflater, parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, feed: Feed) {
        if (holder is ImageViewHolder) {
            holder.bind(feed)
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder is ImageViewHolder) {
            // Reset the ImageView's image resource or any other associated properties
            holder.resetImageView()
        }
    }

    inner class ImageViewHolder(private val binding: LayImgFeedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(feed: Feed) {

            Glide.with(binding.root.context)
                .load(feed.publisher.profilePictureUrl) // Use imgPost.imgUrl instead of ImagePostsDtc.imgUrl
                .placeholder(R.drawable.ic_refresh) // Placeholder image while loading
                .error(R.drawable.ic_add) // Image to display in case of an error
                .into(binding.profileImgFd)

            binding.profileNameFd.text = feed.publisher.fullName

            binding.imgPostTitleFd.text = feed.captionText

            Glide.with(binding.root.context)
                .load(feed.contentUrl) // Use imgPost.imgUrl instead of ImagePostsDtc.imgUrl
                .placeholder(R.drawable.ic_refresh) // Placeholder image while loading
                .error(R.drawable.ic_add) // Image to display in case of an error
                .into(binding.imgPostFd)


            binding.imgPostLikesCountFd.text = convertNumberToString(feed.likeCount)
            binding.imgPostCommentCountFd.text = convertNumberToString(feed.commentCount)
            binding.imgScoreFd.text =
                binding.imgScoreCFd.context.getString(R.string.gScore, feed.postScore)
            binding.viewsCount.text = convertNumberToString(feed.impressionCount)
            binding.daysAgoFd.text = convertToTimeAgo(feed.postDate)


        }

        fun resetImageView() {
            // Clear Glide resources for profile image
            Glide.with(binding.profileImgFd)
                .clear(binding.profileImgFd)

            // Clear Glide resources for post image
            Glide.with(binding.imgPostFd)
                .clear(binding.imgPostFd)

            // Reset other views to default values
            binding.profileNameFd.text = ""
            binding.imgPostTitleFd.text = ""
            binding.imgPostLikesCountFd.text = "0"
            binding.imgPostCommentCountFd.text = "0"
            binding.imgScoreFd.text = ""
            binding.viewsCount.text = "0"
            binding.daysAgoFd.text = ""
        }
    }
}
