package com.example.a675c82563c4c30f10c817c9530a3d8a6.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.room.dao.SatelliteDao
import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.room.entity.SatelliteEntity

@Database(
    entities = [SatelliteEntity::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    companion object {
        const val DB_NAME = "AppDatabase"
    }

    abstract fun satelliteDao(): SatelliteDao
}