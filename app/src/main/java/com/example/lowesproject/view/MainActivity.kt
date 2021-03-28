package com.example.lowesproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
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

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}