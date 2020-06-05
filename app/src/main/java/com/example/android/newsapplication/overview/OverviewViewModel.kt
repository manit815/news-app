/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

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

    init {
        getNewsValues()
    }

    /**private fun getNewsValues() {
//        _response.value = "Set the News API Response here!"
        println("Inside get service #@^%@@%#%^@^#@^%")
        NewsApi.retrofitService.getProperties().enqueue(
            object: Callback<NewsProperty> {
                /**
                 * Invoked when a network exception occurred talking to the server or when an unexpected
                 * exception occurred creating the request or processing the response.
                 */
                override fun onFailure(call: Call<NewsProperty>, t: Throwable) {
                    _response.value = "Failure: " + t.message
                }

                /**
                 * Invoked for a received HTTP response.
                 *
                 *
                 * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
                 * Call [Response.isSuccessful] to determine if the response indicates success.
                 */
                override fun onResponse(call: Call<NewsProperty>, response: Response<NewsProperty>) {
//                    _response.value = response.body()


                    val newsObject = response.body()?.articles;
                    println("response%%%%%%%%% $newsObject" );

                    _response.value = "Success: ${response.body()?.articles}"
                }
            })
    }*/

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
}
