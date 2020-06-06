package com.example.android.newsapplication.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.newsapplication.network.NewsApi
import com.example.android.newsapplication.network.NewsArticles
import com.example.android.newsapplication.network.NewsProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class NewsApiStatus { LOADING, ERROR, DONE }
/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the most recent response
    private val _status = MutableLiveData<NewsApiStatus>()

    // The external immutable LiveData for the response String
    val status: LiveData<NewsApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<NewsArticles>>()

    val properties: LiveData<List<NewsArticles>>
        get() = _properties

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(
            viewModelJob + Dispatchers.Main )

    private val _navigateToSelectedArticle = MutableLiveData<NewsArticles>()
    val navigateToSelectedArticle: LiveData<NewsArticles>
        get() = _navigateToSelectedArticle

    init {
        getNewsValues()
    }

    private fun getNewsValues(){
        coroutineScope.launch {
            var getPropertiesDeferred = NewsApi.retrofitService.getProperties()
            try {
                _status.value = NewsApiStatus.LOADING
                var listResult = getPropertiesDeferred.await()
                _status.value = NewsApiStatus.DONE

                if (listResult?.articles.size > 0) {
                    _properties.value = listResult.articles
                }
            } catch (e: Exception) {
                _status.value = NewsApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayNewsArticle(newsArticles: NewsArticles) {
        _navigateToSelectedArticle.value = newsArticles
    }

    fun displayNewsDetailsComplete() {
        _navigateToSelectedArticle.value = null
    }
}
