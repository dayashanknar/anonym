package com.evourse.NovaRaze.ui.adapters.delegateAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.evourse.instaclone.R
import com.evourse.instaclone.convertNumberToString
import com.evourse.instaclone.convertToTimeAgo
import com.evourse.instaclone.databinding.LayFeedVideoBinding
import com.evourse.instaclone.model.Feed

class VideoDelegateAdapter : DelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayFeedVideoBinding.inflate(inflater, parent, false)
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, feed: Feed) {
        if (holder is VideoViewHolder) {
            holder.bind(feed)
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder is VideoViewHolder) {
            // Reset the ImageView's image resource or any other associated properties
            holder.resetPlayerView()
        }
    }

    inner class VideoViewHolder(private val binding: LayFeedVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(feed: Feed) {
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


            binding.imgPostLikesCountFd.text = convertNumberToString(feed.likeCount)
            binding.imgPostCommentCountFd.text = convertNumberToString(feed.commentCount)
            binding.imgScoreFd.text =
                binding.imgScoreCFd.context.getString(R.string.gScore, feed.postScore)
            binding.viewsCount.text = convertNumberToString(feed.impressionCount)
            binding.daysAgoFd.text = convertToTimeAgo(feed.postDate)

        }

        fun resetPlayerView() {
            val player = ExoPlayer.Builder(binding.root.context).build()

            // Release the player resources
            player.release()

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
