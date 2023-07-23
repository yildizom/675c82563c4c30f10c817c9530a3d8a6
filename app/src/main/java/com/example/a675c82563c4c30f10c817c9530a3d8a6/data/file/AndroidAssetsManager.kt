package com.example.a675c82563c4c30f10c817c9530a3d8a6.data.file

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AndroidAssetsManager(private val context: Context): AssetsManager {
    override fun <T> readAssets(path: String): T {
        val jsonString = context.assets.open(path).bufferedReader().use { it.readText() }
        val typeToken = object: TypeToken<T>(){}.type
        return Gson().fromJson(jsonString, typeToken)
    }
}