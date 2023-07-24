package com.example.a675c82563c4c30f10c817c9530a3d8a6.di

import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.file.AssetsManager
import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.repository.SatelliteRepository
import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.repository.SatelliteRepositoryImpl
import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.room.dao.SatelliteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideSatelliteRepository(
        satelliteDao: SatelliteDao,
        assetsManager: AssetsManager
    ): SatelliteRepository {
        return SatelliteRepositoryImpl(satelliteDao, assetsManager)
    }
}