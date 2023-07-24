package com.example.a675c82563c4c30f10c817c9530a3d8a6.ui.list

import androidx.lifecycle.ViewModel
import com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.usecase.GetSatellitesUseCase
import javax.inject.Inject

class ListViewModel @Inject constructor(
    getSatellitesUseCase: GetSatellitesUseCase
): ViewModel() {

    val items = getSatellitesUseCase()
}