package com.example.a675c82563c4c30f10c817c9530a3d8a6.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.model.Position
import com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.model.Res
import com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.usecase.GetSatelliteDetailUseCase
import com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.usecase.GetSatellitePositionsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val getSatelliteDetailUseCase: GetSatelliteDetailUseCase,
    private val getSatellitePositionsUseCase: GetSatellitePositionsUseCase
): ViewModel() {

    private val _positions = MutableStateFlow<Position?>(null)
    val positions get() = _positions.asStateFlow()

    fun retrieveDataById(id: Int) = getSatelliteDetailUseCase(id).also {
        retrievePositions(id)
    }

    private fun retrievePositions(id: Int) {
        getSatellitePositionsUseCase(id).onEach {
            if (it is Res.Success) {
                updatePositionInfo(it.data.positions)
            }
        }.launchIn(viewModelScope)
    }

    private suspend fun updatePositionInfo(positions: List<Position>) {
        var index = 0
        while (true) {
            _positions.emit(positions[index])
            index = if (positions.lastIndex > index) index + 1 else 0
            delay(3000)
        }
    }
}