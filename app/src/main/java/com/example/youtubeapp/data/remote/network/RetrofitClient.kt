package com.example.youtubeapp.data.remote.network

import com.example.youtubeapp.data.remote.network.apiservisec.YouTubeApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    companion object {
        private val okHttpClient: OkHttpClient =
            OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()

        private val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.googleapis.com/youtube/v3/")
            .client(okHttpClient)
            .build()

        fun instanceRetrofit(): YouTubeApi {
            return retrofit.create(YouTubeApi::class.java)
        }
    }
}