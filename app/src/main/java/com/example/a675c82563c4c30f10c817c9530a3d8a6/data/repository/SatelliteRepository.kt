package com.example.a675c82563c4c30f10c817c9530a3d8a6.data.repository

import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.file.dto.PositionsDto
import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.file.dto.SatelliteDetailDto
import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.file.dto.SatelliteDto

interface SatelliteRepository {
    suspend fun getSatellites(): Array<SatelliteDto>
    suspend fun getSatelliteDetailByIdFromAssets(id: Int): SatelliteDetailDto?
    suspend fun getSatelliteDetailByIdFromDb(id: Int): SatelliteDetailDto?
    suspend fun getPositionsBySatelliteId(id: Int): PositionsDto?
    suspend fun saveSatelliteDetail(satelliteDetailDto: SatelliteDetailDto)
}