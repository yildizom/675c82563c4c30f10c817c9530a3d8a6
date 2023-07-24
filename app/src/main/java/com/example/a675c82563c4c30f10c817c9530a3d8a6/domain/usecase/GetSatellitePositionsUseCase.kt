package com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.usecase

import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.repository.SatelliteRepository
import com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.model.Res
import com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.model.SatellitePositions
import com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.model.toSatellitePositions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSatellitePositionsUseCase @Inject constructor(private val satelliteRepository: SatelliteRepository) {

    operator fun invoke(id: Int): Flow<Res<SatellitePositions>> = flow {
        val positions = satelliteRepository.getPositionsBySatelliteId(id)?.toSatellitePositions()
        if (positions != null) {
            emit(Res.Success(positions))
            return@flow
        }
        emit(Res.Error("Not found"))
    }
}