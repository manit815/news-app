package com.example.android.newsapplication.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.newsapplication.network.NewsArticles

/**
 * The [ViewModel] that is associated with the [DetailFragment].
 */
class DetailViewModel(@Suppress("UNUSED_PARAMETER")newsArticles: NewsArticles, app: Application) : AndroidViewModel(app) {
    private val _selectedArticles = MutableLiveData<NewsArticles>()
    val selectedArticles: LiveData<NewsArticles>
        get() = _selectedArticles

    init {
        _selectedArticles.value = newsArticles
    }

}
