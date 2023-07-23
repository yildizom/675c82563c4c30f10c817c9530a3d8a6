package com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.model

import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.file.dto.PositionsDto
import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.file.dto.SatellitePositionDto

data class SatellitePositions(val id: Int, val positions: List<Position>)

data class Position(val posX: Float, val posY: Float)

fun SatellitePositionDto.toSatellitePositions(): SatellitePositions {
    return SatellitePositions(
        id = id,
        positions = positions.map {
            Position(posX = it.posX, posY = it.posY)
        }
    )
}

fun PositionsDto.toPositionArray(): List<SatellitePositions> = list.map { it.toSatellitePositions() }