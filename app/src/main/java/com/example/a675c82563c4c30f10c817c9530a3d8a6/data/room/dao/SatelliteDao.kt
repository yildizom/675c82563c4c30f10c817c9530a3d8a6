package com.example.a675c82563c4c30f10c817c9530a3d8a6.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.room.entity.SatelliteEntity

@Dao
interface SatelliteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(satelliteEntity: SatelliteEntity): Long

    @Query("SELECT * FROM satellite WHERE id == :id")
    suspend fun getById(id: Int): SatelliteEntity?
}