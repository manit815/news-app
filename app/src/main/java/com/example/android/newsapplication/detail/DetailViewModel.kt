package com.example.android.newsapplication.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.android.newsapplication.network.NewsProperty

/**
 * The [ViewModel] that is associated with the [DetailFragment].
 */
class DetailViewModel(@Suppress("UNUSED_PARAMETER")newsProperty: NewsProperty, app: Application) : AndroidViewModel(app) {
}
