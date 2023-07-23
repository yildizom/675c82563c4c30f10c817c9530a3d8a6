package com.example.a675c82563c4c30f10c817c9530a3d8a6.data.file.dto

data class PositionsDto(val list: List<SatellitePositionDto>)

data class SatellitePositionDto(val id: Int, val positions: List<PositionDto>)

data class PositionDto(val posX: Float, val posY: Float)