package com.example.a675c82563c4c30f10c817c9530a3d8a6.data.file.dto

import com.google.gson.annotations.SerializedName
import java.util.Date

data class SatelliteDetailDto(
    val id: Int,
    @SerializedName("cost_per_launch")
    val costPerLaunch: Int,
    @SerializedName("first_flight")
    val firstFlight: Date,
    val height: Int,
    val mass: Int
)