package com.example.a675c82563c4c30f10c817c9530a3d8a6.di

import android.content.Context
import androidx.room.Room
import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.room.AppDatabase
import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.room.dao.SatelliteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, AppDatabase.DB_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideRecordDao(database: AppDatabase): SatelliteDao = database.satelliteDao()
}