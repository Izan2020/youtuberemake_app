package com.glantrox.youtubelite.core.database

import android.util.Log
import com.glantrox.youtubelite.core.models.YoutubeVideoResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Repository {
    companion object {
        const val apiKey = "YOUR_APIKEY"

        fun create(): Repository {



            return  Retrofit.Builder()
                .baseUrl("https://youtube.googleapis.com/youtube/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Repository::class.java)
        }
    }
    @GET("videos")
    suspend fun listOfPopularVideos(
        @Query("part") part: String = "snippet,contentDetails,statistics",
        @Query("chart") chart: String = "mostPopular",
        @Query("regionCode") regionCode: String = "ID",
        @Query("maxResults") maxResult: Int = 50,
        @Query("key") key: String = apiKey
    ): YoutubeVideoResponse

    @GET("videos")
    suspend fun getDetailVideoById(
        @Query("part") part: String = "snippet,contentDetails,statistics",
        @Query("id") id: String ,
        @Query("key") key: String = apiKey
    ): YoutubeVideoResponse

}