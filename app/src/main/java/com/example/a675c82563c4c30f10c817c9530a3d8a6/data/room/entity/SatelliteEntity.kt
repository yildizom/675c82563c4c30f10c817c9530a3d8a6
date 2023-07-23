package com.example.a675c82563c4c30f10c817c9530a3d8a6.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.file.dto.SatelliteDetailDto
import java.util.Date

@Entity(tableName = "satellite")
data class SatelliteEntity(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    val costPerLaunch: Int,
    val firstFlight: Long,
    val height: Int,
    val mass: Int
) {
    fun toSatelliteDetailDto(): SatelliteDetailDto {
        return SatelliteDetailDto(
            id = id,
            costPerLaunch = costPerLaunch,
            firstFlight = Date(firstFlight),
            height = height,
            mass = mass
        )
    }
}

fun SatelliteDetailDto.toSatelliteEntity(): SatelliteEntity {
    return SatelliteEntity(
        id = id,
        costPerLaunch = costPerLaunch,
        firstFlight = firstFlight.time,
        height = height,
        mass = mass
    )
}