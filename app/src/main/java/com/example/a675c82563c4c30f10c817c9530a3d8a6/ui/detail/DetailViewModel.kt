package com.example.a675c82563c4c30f10c817c9530a3d8a6.ui.detail

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.model.Position
import com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.model.Res
import com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.model.SatelliteDetail
import com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.usecase.GetSatelliteDetailUseCase
import com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.usecase.GetSatellitePositionsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val getSatelliteDetailUseCase: GetSatelliteDetailUseCase,
    private val getSatellitePositionsUseCase: GetSatellitePositionsUseCase
): ViewModel() {

    val ofSatellite = ObservableField<SatelliteDetail>()
    val ofPositions = ObservableField<Position>()

    fun retrieveDataById(id: Int) {
        retrieveSatelliteDetail(id)
        retrievePositions(id)
    }

    private fun retrieveSatelliteDetail(id: Int) {
        getSatelliteDetailUseCase(id).onEach {
            if (it is Res.Success) {
                onSuccess(it.data)
            }
        }.launchIn(viewModelScope)
    }

    private fun retrievePositions(id: Int) {
        getSatellitePositionsUseCase(id).onEach {
            if (it is Res.Success) {
                updatePositionInfo(it.data.positions)
            }
        }.launchIn(viewModelScope)
    }

    private suspend fun updatePositionInfo(positions: List<Position>, index: Int = 0) {
        ofPositions.set(positions[index])
        val nextIndex = if (positions.lastIndex > index) index + 1 else 0
        delay(3000)
        updatePositionInfo(positions, nextIndex)
    }

    private fun onSuccess(data: SatelliteDetail) {
        ofSatellite.set(data)
    }
}