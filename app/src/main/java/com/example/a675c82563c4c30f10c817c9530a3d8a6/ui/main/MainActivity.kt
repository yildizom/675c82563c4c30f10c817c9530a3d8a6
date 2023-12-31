package com.example.a675c82563c4c30f10c817c9530a3d8a6.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a675c82563c4c30f10c817c9530a3d8a6.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}