package com.example.android.newsapplication.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.newsapplication.network.NewsProperty

class DetailViewModelFactory(
        private val newsProperty: NewsProperty,
        private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(newsProperty, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
