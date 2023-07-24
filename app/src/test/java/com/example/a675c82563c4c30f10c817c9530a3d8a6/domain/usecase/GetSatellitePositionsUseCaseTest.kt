package com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.usecase

import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.repository.FakeSatelliteRepository
import com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.model.Res
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetSatellitePositionsUseCaseTest {

    private lateinit var getSatellitePositionsUseCase: GetSatellitePositionsUseCase

    @Before
    fun setup() {
        getSatellitePositionsUseCase = GetSatellitePositionsUseCase(FakeSatelliteRepository())
    }

    @Test
    fun existentSatelliteIdReturnsSuccess() = runBlocking {
        val result = getSatellitePositionsUseCase(1).last()
        Assert.assertTrue(result is Res.Success)
    }

    @Test
    fun nonexistentSatelliteIdReturnsError() = runBlocking {
        val result = getSatellitePositionsUseCase(-1).last()
        Assert.assertTrue(result is Res.Error)
    }
}