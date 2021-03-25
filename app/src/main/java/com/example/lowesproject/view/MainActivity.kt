package com.example.lowesproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lowesproject.R
import com.example.lowesproject.databinding.ActivityMainBinding
import com.example.lowesproject.viewmodel.WeatherViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val viewModel = WeatherViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}