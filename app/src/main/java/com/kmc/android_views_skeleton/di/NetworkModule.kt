package com.kmc.android_views_skeleton.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kmc.android_views_skeleton.utils.Constants
import com.kmc.android_views_skeleton.utils.networking.api.ChuckNorrisApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    @Singleton
    fun okhttpClient(): OkHttpClient {

        val logging = HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .callTimeout( 30, TimeUnit.SECONDS)
            .connectTimeout( 30, TimeUnit.SECONDS)
            .writeTimeout( 30, TimeUnit.SECONDS)
            .readTimeout( 30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ChuckNorrisApi {
        return retrofit.create(ChuckNorrisApi::class.java)
    }
}