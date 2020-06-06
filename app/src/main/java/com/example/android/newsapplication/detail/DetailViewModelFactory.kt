package com.example.android.newsapplication.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.newsapplication.network.NewsArticles
import com.example.android.newsapplication.network.NewsProperty

class DetailViewModelFactory(
        private val newsArticles: NewsArticles,
        private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(newsArticles, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
