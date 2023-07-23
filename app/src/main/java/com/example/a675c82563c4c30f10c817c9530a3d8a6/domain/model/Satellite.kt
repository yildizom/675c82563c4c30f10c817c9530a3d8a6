package com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.model

import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.file.dto.SatelliteDto

data class Satellite(val id: Int, val active: Boolean, val name: String)

fun SatelliteDto.toSatellite() = Satellite(id = id, active = active, name = name)
