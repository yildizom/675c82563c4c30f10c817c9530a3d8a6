package com.example.a675c82563c4c30f10c817c9530a3d8a6.data.file

import android.content.Context

class AndroidAssetsManager(private val context: Context): AssetsManager {
    override fun readAssets(path: String): String {
        return context.assets.open(path).bufferedReader().use { it.readText() }
    }
}