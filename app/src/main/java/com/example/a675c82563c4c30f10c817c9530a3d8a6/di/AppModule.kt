package com.example.a675c82563c4c30f10c817c9530a3d8a6.di

import android.content.Context
import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.file.AndroidAssetsManager
import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.file.AssetsManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAssetsManager(@ApplicationContext applicationContext: Context): AssetsManager {
        return AndroidAssetsManager(applicationContext)
    }
}