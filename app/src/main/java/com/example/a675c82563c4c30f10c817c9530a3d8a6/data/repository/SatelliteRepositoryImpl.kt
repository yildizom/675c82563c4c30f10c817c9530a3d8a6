package com.example.a675c82563c4c30f10c817c9530a3d8a6.data.repository

import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.file.AssetsManager
import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.file.dto.PositionsDto
import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.file.dto.SatelliteDetailDto
import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.file.dto.SatelliteDto
import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.room.dao.SatelliteDao
import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.room.entity.toSatelliteEntity

class SatelliteRepositoryImpl constructor(
    private val satelliteDao: SatelliteDao,
    private val assetsManager: AssetsManager
): SatelliteRepository {
    override suspend fun getSatellites(): Array<SatelliteDto> {
        val satellitesPath = "satellites.json"
        return assetsManager.readAssets(satellitesPath, Array<SatelliteDto>::class.java)
    }

    override suspend fun getSatelliteDetailByIdFromAssets(id: Int): SatelliteDetailDto? {
        val satelliteDetailPath = "satellite-detail.json"
        return assetsManager
            .readAssets(satelliteDetailPath, Array<SatelliteDetailDto>::class.java)
            .firstOrNull { it.id == id }
    }

    override suspend fun getSatelliteDetailByIdFromDb(id: Int): SatelliteDetailDto? {
        return satelliteDao.getById(id)?.toSatelliteDetailDto()
    }

    override suspend fun getPositionsBySatelliteId(id: Int): PositionsDto {
        val positionsPath = "positions.json"
        return assetsManager.readAssets(positionsPath, PositionsDto::class.java)
    }

    override suspend fun saveSatelliteDetail(satelliteDetailDto: SatelliteDetailDto) {
        satelliteDao.insert(satelliteDetailDto.toSatelliteEntity())
    }
}