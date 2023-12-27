package com.evourse.instaclone.viewHolder

import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.evourse.instaclone.R
import com.evourse.instaclone.convertToTimeAgo
import com.evourse.instaclone.databinding.LayAudioPostsBinding
import com.evourse.instaclone.databinding.LayFeedVideoBinding
import com.evourse.instaclone.databinding.LayImgFeedBinding
import com.evourse.instaclone.databinding.LayTextPostBinding
import com.evourse.instaclone.model.Feed

fun formatNumber(number: Int): String {
    return when {
        number < 1000 -> "$number"
        number < 1_000_000 -> String.format("%.1fK", number / 1000.0)
        else -> String.format("%.1fM", number / 1_000_000.0)
    }
}

sealed class FeedViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(feed: Feed)

    class ImageViewHolder(private val binding: LayImgFeedBinding) :
        FeedViewHolder(binding) {
        override fun bind(feed: Feed) {
            // Bind image data to views using the binding

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


            binding.imgPostLikesCountFd.text = formatNumber(feed.likeCount)
            binding.imgPostCommentCountFd.text = formatNumber(feed.commentCount)
            binding.imgScoreFd.text =
                binding.imgScoreCFd.context.getString(R.string.gScore, feed.postScore)
            binding.viewsCount.text = formatNumber(feed.impressionCount)
            binding.daysAgoFd.text = convertToTimeAgo(feed.postDate)


            // Other binding logic
//            binding.executePendingBindings()
        }
    }

    class VideoViewHolder(private val binding: LayFeedVideoBinding) :
        FeedViewHolder(binding) {

        override fun bind(feed: Feed) {
            // Bind video data to views using the binding
            Glide.with(binding.root.context)
                .load(feed.publisher.profilePictureUrl) // Use imgPost.imgUrl instead of ImagePostsDtc.imgUrl
                .placeholder(R.drawable.ic_refresh) // Placeholder image while loading
                .error(R.drawable.ic_add) // Image to display in case of an error
                .into(binding.profileImgFd)

            binding.profileNameFd.text = feed.publisher.fullName

            binding.imgPostTitleFd.text = feed.captionText

            val playerView = binding.feedVideoPlayer

            // Create an ExoPlayer instance
            val player = ExoPlayer.Builder(binding.root.context).build()

            // Attach the player to the PlayerView
            playerView.player = player

            // Set up a MediaItem and prepare the player
            val mediaItem = MediaItem.fromUri(feed.contentUrl ?: "")
            player.setMediaItem(mediaItem)
            player.prepare()
//            player.play()


            binding.imgPostLikesCountFd.text = formatNumber(feed.likeCount)
            binding.imgPostCommentCountFd.text = formatNumber(feed.commentCount)
            binding.imgScoreFd.text =
                binding.imgScoreCFd.context.getString(R.string.gScore, feed.postScore)
            binding.viewsCount.text = formatNumber(feed.impressionCount)
            binding.daysAgoFd.text = convertToTimeAgo(feed.postDate)

            // Other binding logic
//            binding.executePendingBindings()
        }
    }

    class TextViewHolder(private val binding: LayTextPostBinding) :
        FeedViewHolder(binding) {

        override fun bind(feed: Feed) {
            // Bind video data to views using the binding
            Glide.with(binding.root.context)
                .load(feed.publisher.profilePictureUrl) // Use imgPost.imgUrl instead of ImagePostsDtc.imgUrl
                .placeholder(R.drawable.ic_refresh) // Placeholder image while loading
                .error(R.drawable.ic_add) // Image to display in case of an error
                .into(binding.profileImgFd)

            binding.profileNameFd.text = feed.publisher.fullName

            binding.textPostTitle.text = feed.captionText
            binding.textPostLikesCountFd.text = formatNumber(feed.likeCount)
            binding.textPostCommentCountFd.text = formatNumber(feed.commentCount)
            binding.textScoreFd.text =
                binding.textScoreCFd.context.getString(R.string.gScore, feed.postScore)
            binding.viewsCount.text = formatNumber(feed.impressionCount)
            binding.textPubDate.text = convertToTimeAgo(feed.postDate)

        }
    }


    class AudioViewHolder(private val binding: LayAudioPostsBinding) :
        FeedViewHolder(binding) {

        override fun bind(feed: Feed) {
            // Bind video data to views using the binding
            Glide.with(binding.root.context)
                .load(feed.publisher.profilePictureUrl) // Use imgPost.imgUrl instead of ImagePostsDtc.imgUrl
                .placeholder(R.drawable.ic_refresh) // Placeholder image while loading
                .error(R.drawable.ic_add) // Image to display in case of an error
                .into(binding.profileImgFd)

            binding.profileNameFd.text = feed.publisher.fullName

            binding.audPubDate.text = convertToTimeAgo(feed.postDate)

            binding.audTitle.text = feed.captionText
            binding.audViewCount.text = formatNumber(feed.impressionCount)


        }
    }
}
