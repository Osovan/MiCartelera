package com.osovan.micartelera.di

import android.app.Application
import com.osovan.micartelera.R
import com.osovan.micartelera.data.remote.MoviedbApiClient
import com.osovan.micartelera.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

     @Provides
     @Singleton
     fun provideRetrofit(): MoviedbApiClient =
          Retrofit.Builder()
               .baseUrl(BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .build()
               .create(MoviedbApiClient::class.java)

     @Provides
     @Singleton
     @Named("apiKey")
     fun provideApiKey(app: Application): String = app.getString(R.string.api_key)
}