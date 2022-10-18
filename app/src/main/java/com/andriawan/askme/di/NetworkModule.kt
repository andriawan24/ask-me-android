package com.andriawan.askme.di

import android.content.Context
import com.andriawan.askme.BuildConfig
import com.andriawan.askme.data.network.AskMeAPI
import com.andriawan.askme.data.network.AuthorizationHeaderInterceptor
import com.andriawan.askme.utils.Constants.TIMEOUT_DEFAULT
import com.andriawan.askme.utils.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun providesOkHttpClient(sharedPreferencesHelper: SharedPreferencesHelper): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_DEFAULT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_DEFAULT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_DEFAULT, TimeUnit.SECONDS)
            .addInterceptor(AuthorizationHeaderInterceptor(sharedPreferencesHelper))
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): AskMeAPI = retrofit.create(AskMeAPI::class.java)
}