package com.example.android.newsapplication.network

import com.example.android.newsapplication.BuildConfig
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://newsapi.org/v2/"

private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

interface NewsApiService {
    @GET("top-headlines?sources=bbc-news&apiKey=ca15b7eafc9d4db696b7d686a93cc069")
    fun getProperties():
            Call<String>
}

object NewsApi {
    val retrofitService : NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java) }
}