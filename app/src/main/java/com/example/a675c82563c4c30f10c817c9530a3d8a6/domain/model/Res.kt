package com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.model

sealed class Res<out T> {
    data class Success<T>(val data: T): Res<T>()
    data class Error(val message: String? = null): Res<Nothing>()
    object Loading: Res<Nothing>()
}