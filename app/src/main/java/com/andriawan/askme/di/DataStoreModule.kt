package com.andriawan.askme.di

import android.content.Context
import com.andriawan.askme.data.local.datastore.AskMeDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun providesDataStore(
        @ApplicationContext context: Context
    ): AskMeDataStore = AskMeDataStore(context)
}