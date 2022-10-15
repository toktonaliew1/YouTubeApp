package com.example.youtubeapp.di

import android.content.Context
import androidx.room.Room
import com.example.youtubeapp.data.dao.YoutubeDataBase
import com.example.youtubeapp.data.remote.network.apiservisec.YouTubeApi
import com.example.youtubeapp.data.repository.YoutubeRepository
import com.example.youtubeapp.presentation.ui.fragments.details.DetailsViewModel
import com.example.youtubeapp.presentation.ui.activity.MainViewModel

import com.example.youtubeapp.presentation.ui.fragments.noInternet.NoInternetViewModel
import com.example.youtubeapp.presentation.ui.fragments.playlists.PlaylistViewModel
import com.example.youtubeapp.presentation.ui.fragments.video.VideoDetailViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val vmModule = module {
    viewModel { PlaylistViewModel(get()) }
    viewModel { MainViewModel() }
    viewModel { DetailsViewModel(get()) }
    viewModel { NoInternetViewModel() }

}

val appModule = module {
    single { androidContext().resources }

}

val networkModule = module {
    single { provideRetrofit(get()) }
    single { provideOkhttpClient() }
    factory { YouTubeApi.provideYoutubeApi(get()) }
}

val repositoryModule = module {
    factory { YoutubeRepository(get(), get())}
}

val localModule = module {
    single { provideDatabase(androidContext()) }
}

fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.googleapis.com/youtube/v3/")
            .client(client)
            .build()
}

fun provideOkhttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder() //для ограничения времени
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
}

fun provideDatabase(context: Context): YoutubeDataBase {
    return Room.databaseBuilder(
            context.applicationContext,
            YoutubeDataBase::class.java,
            "word_database"
    )
            .allowMainThreadQueries()
            .build()
}