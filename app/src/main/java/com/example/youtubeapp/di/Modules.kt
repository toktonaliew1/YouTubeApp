package com.example.youtubeapp.di

import android.content.Context
import androidx.room.Room
import com.example.youtubeapp.data.dao.YoutubeDataBase
import com.example.youtubeapp.data.remote.network.RetrofitClient
import com.example.youtubeapp.data.remote.network.apiservisec.YouTubeApi
import com.example.youtubeapp.data.repository.YoutubeRepositoryImpl
import com.example.youtubeapp.presentation.ui.fragments.details.DetailsViewModel

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
    viewModel { DetailsViewModel(get()) }
    viewModel { NoInternetViewModel() }
    viewModel { VideoDetailViewModel(get()) }
}

val appModule = module {
    single { androidContext().resources }
}

val networkModule = module {
    single { RetrofitClient.provideRetrofit(get()) }
    single { RetrofitClient.provideOkhttpClient() }
    factory { RetrofitClient.provideYoutubeApi() }
}

val repositoryModule = module {
    factory { YoutubeRepositoryImpl(get(), get()) }
}

val localModule = module {
    single { YoutubeDataBase.getDatabase(androidContext()) }
}





