package com.example.a675c82563c4c30f10c817c9530a3d8a6.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.model.Res
import com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.model.Satellite
import com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.usecase.GetSatellitesUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel @Inject constructor(
    private val getSatellitesUseCase: GetSatellitesUseCase
): ViewModel() {

    private val _items = MutableSharedFlow<Res<List<Satellite>>>()
    val items get() = _items

    private val searchDelay: Long = 800L
    private var searchJob: Job? = null

    init {
        getItems()
    }

    fun getItems(query: String = "") {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(searchDelay)
            getSatellitesUseCase(query).onEach {
                _items.emit(it)
            }.launchIn(this)
        }
    }
}