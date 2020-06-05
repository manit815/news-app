package com.example.android.newsapplication.network

data class NewsArticles (
//        val id: String,
        val source: Object? = null,
        val author: String? = null,
        val title: String? = null,
        val description: String? = null,
        val url: String? = null,
        val urlToImage: String? = null,
        val publishedAt: String? = null,
        val content: String? = null
)