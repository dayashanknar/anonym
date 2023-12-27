package com.evourse.instaclone

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun convertToTimeAgo(timestamp: String): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val currentDate = Date()

    try {
        val pubDate = dateFormat.parse(timestamp)
        val diffInMillis = currentDate.time - pubDate.time

        val diffInSeconds = diffInMillis / 1000
        val diffInMinutes = diffInSeconds / 60
        val diffInHours = diffInMinutes / 60
        val diffInDays = diffInHours / 24
        val diffInMonths = diffInDays / 30
        val diffInYears = diffInDays / 365

        return when {
            diffInSeconds < 60 -> "$diffInSeconds seconds ago"
            diffInMinutes < 60 -> "$diffInMinutes minutes ago"
            diffInHours < 24 -> "$diffInHours hours ago"
            diffInDays < 30 -> if (diffInDays.toInt() == 1) "yesterday" else "$diffInDays days ago"
            diffInMonths < 12 -> if (diffInMonths.toInt() == 1) "a month ago" else "$diffInMonths months ago"
            else -> if (diffInYears.toInt() == 1) "a year ago" else "$diffInYears years ago"
        }

    } catch (e: Exception) {
        e.printStackTrace()
    }

    return ""
}
