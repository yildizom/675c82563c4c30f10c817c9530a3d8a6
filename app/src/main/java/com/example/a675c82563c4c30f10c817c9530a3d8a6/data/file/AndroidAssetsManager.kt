package com.example.a675c82563c4c30f10c817c9530a3d8a6.data.file

import android.content.Context
import com.google.gson.Gson

class AndroidAssetsManager(private val context: Context): AssetsManager {
    override fun <T> readAssets(path: String, clazz: Class<T>): T {
        val jsonString = context.assets.open(path).bufferedReader().use { it.readText() }
        return Gson().fromJson(jsonString, clazz)
    }
}