package com.example.a675c82563c4c30f10c817c9530a3d8a6.data.repository

import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.file.dto.PositionDto
import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.file.dto.PositionsDto
import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.file.dto.SatelliteDetailDto
import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.file.dto.SatelliteDto
import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.file.dto.SatellitePositionDto
import java.util.Date

class FakeSatelliteRepository: SatelliteRepository {
    private val satellites = arrayOf(
        SatelliteDto(1, false, "first-fake-satellite"),
        SatelliteDto(2, true, "second-fake-satellite"),
        SatelliteDto(3, false, "third-fake-satellite")
    )

    private val satelliteDetailsFromAssets = arrayOf(
        SatelliteDetailDto(1, 200, Date(), 100, 1200),
        SatelliteDetailDto(2, 200, Date(), 100, 1200),
        SatelliteDetailDto(3, 200, Date(), 100, 1200)
    )

    private val satelliteDetailsFromDb = mutableListOf(
        SatelliteDetailDto(1, 200, Date(), 100, 1200)
    )

    private val positions = PositionsDto(
        listOf(
            SatellitePositionDto(
                1,
                listOf(
                    PositionDto(0.1011f, 0.1021f),
                    PositionDto(0.1012f, 0.1022f),
                    PositionDto(0.1013f, 0.1023f),
                )
            ),
            SatellitePositionDto(
                2,
                listOf(
                    PositionDto(0.2011f, 0.2021f),
                    PositionDto(0.2012f, 0.2022f),
                    PositionDto(0.2013f, 0.2023f),
                )
            ),
            SatellitePositionDto(
                3,
                listOf(
                    PositionDto(0.3011f, 0.3021f),
                    PositionDto(0.3012f, 0.3022f),
                    PositionDto(0.3013f, 0.3023f),
                )
            )
        )
    )

    override suspend fun getSatellites(): Array<SatelliteDto> {
        return satellites
    }

    override suspend fun getSatelliteDetailByIdFromAssets(id: Int): SatelliteDetailDto? {
        return satelliteDetailsFromAssets.find { it.id == id }
    }

    override suspend fun getSatelliteDetailByIdFromDb(id: Int): SatelliteDetailDto? {
        return satelliteDetailsFromDb.find { it.id == id }
    }

    override suspend fun getPositionsBySatelliteId(id: Int): SatellitePositionDto? {
        return positions.list.firstOrNull { it.id == id }
    }

    override suspend fun saveSatelliteDetail(satelliteDetailDto: SatelliteDetailDto) {
        val itemFromDb = satelliteDetailsFromDb.find { it.id == satelliteDetailDto.id }
        val index = satelliteDetailsFromDb.indexOf(itemFromDb)
        if (index != -1) {
            satelliteDetailsFromDb.add(satelliteDetailDto)
        }
    }
}