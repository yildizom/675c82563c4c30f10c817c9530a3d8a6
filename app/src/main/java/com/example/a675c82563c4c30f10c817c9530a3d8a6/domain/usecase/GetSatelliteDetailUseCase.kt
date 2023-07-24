package com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.usecase

import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.repository.SatelliteRepository
import com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.model.Res
import com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.model.SatelliteDetail
import com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.model.toSatelliteDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSatelliteDetailUseCase @Inject constructor(private val satelliteRepository: SatelliteRepository) {

    operator fun invoke(id: Int): Flow<Res<SatelliteDetail>> = flow {
        emit(Res.Loading)
        var satelliteDetail =
            satelliteRepository.getSatelliteDetailByIdFromDb(id)?.toSatelliteDetail()
        if (satelliteDetail != null) {
            emit(Res.Success(satelliteDetail))
            return@flow
        }
        satelliteDetail =
            satelliteRepository.getSatelliteDetailByIdFromAssets(id)?.toSatelliteDetail()
        if (satelliteDetail != null) {
            emit(Res.Success(satelliteDetail))
            satelliteRepository.saveSatelliteDetail(satelliteDetail.toSatelliteDetailDto())
            return@flow
        }
        emit(Res.Error("Not found"))
    }
}