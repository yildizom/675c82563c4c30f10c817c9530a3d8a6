package com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.usecase

import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.repository.FakeSatelliteRepository
import com.example.a675c82563c4c30f10c817c9530a3d8a6.data.repository.SatelliteRepository
import com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.model.Res
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetSatelliteDetailUseCaseTest {

    private lateinit var getSatelliteDetailUseCase: GetSatelliteDetailUseCase
    private lateinit var repository: SatelliteRepository

    @Before
    fun setup() {
        repository = FakeSatelliteRepository()
        getSatelliteDetailUseCase = GetSatelliteDetailUseCase(repository)
    }

    @Test
    fun existentSatelliteDetailIsCached() = runBlocking {
        getSatelliteDetailUseCase(1)
        val result = repository.getSatelliteDetailByIdFromDb(1)
        Assert.assertTrue(result != null)
    }

    @Test
    fun checksCacheFirst() = runBlocking {
        val result = getSatelliteDetailUseCase(4).last()
        Assert.assertTrue(result is Res.Success)
    }

    @Test
    fun nonexistentSatelliteIdReturnsError() = runBlocking {
        val result = getSatelliteDetailUseCase(-1).last()
        Assert.assertTrue(result is Res.Error)
    }
}