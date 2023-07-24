package com.example.a675c82563c4c30f10c817c9530a3d8a6.data.repository

import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.file.dto.SatelliteDetailDto
import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.file.dto.SatelliteDto
import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.file.dto.SatellitePositionDto

interface SatelliteRepository {
    suspend fun getSatellites(): Array<SatelliteDto>
    suspend fun getSatelliteDetailByIdFromAssets(id: Int): SatelliteDetailDto?
    suspend fun getSatelliteDetailByIdFromDb(id: Int): SatelliteDetailDto?
    suspend fun getPositionsBySatelliteId(id: Int): SatellitePositionDto?
    suspend fun saveSatelliteDetail(satelliteDetailDto: SatelliteDetailDto)
}