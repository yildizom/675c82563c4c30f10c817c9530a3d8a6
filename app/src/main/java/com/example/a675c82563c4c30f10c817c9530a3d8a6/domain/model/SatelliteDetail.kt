package com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.model

import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.file.dto.SatelliteDetailDto
import java.util.Date

data class SatelliteDetail(
    val id: Int,
    val costPerLaunch: Int,
    val firstFlight: Date,
    val height: Int,
    val mass: Int
)

fun SatelliteDetailDto.toSatelliteDetail(): SatelliteDetail {
    return SatelliteDetail(
        id = id,
        costPerLaunch = costPerLaunch,
        firstFlight = firstFlight,
        height = height,
        mass = mass
    )
}