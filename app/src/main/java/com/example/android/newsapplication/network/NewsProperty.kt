package com.example.android.newsapplication.network

data class NewsProperty(
    val status: String,
    val source: String,
    val sortBy: String,
    val articles: List<NewsArticles>
)
