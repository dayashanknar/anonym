package com.evourse.instaclone

fun convertNumberToString(number: Int): String {
    return when {
        number < 1000 -> "$number"
        number < 1_000_000 -> String.format("%.1fK", number / 1000.0)
        else -> String.format("%.1fM", number / 1_000_000.0)
    }
}
