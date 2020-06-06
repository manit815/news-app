package com.example.android.newsapplication.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsArticles (
        val author: String? = null,
        val title: String? = null,
        val description: String? = null,
        val url: String? = null,
        val urlToImage: String? = null,
        val publishedAt: String? = null,
        val content: String? = null
): Parcelable {

}