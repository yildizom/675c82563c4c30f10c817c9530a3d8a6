package com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.usecase

import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.repository.SatelliteRepository
import com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.model.Res
import com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.model.Satellite
import com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.model.toSatellite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSatellitesUseCase @Inject constructor(private val satelliteRepository: SatelliteRepository) {

    operator fun invoke(query: String): Flow<Res<List<Satellite>>> = flow {
        val satellites = satelliteRepository.getSatellites().map { it.toSatellite() }
        if (query.isBlank()) {
            emit(Res.Success(satellites))
            return@flow
        }
        val filtered = satellites.filter { it.name.lowercase().contains(query.lowercase()) }
        emit(Res.Success(filtered))
    }
}