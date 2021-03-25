package com.example.lowesproject.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lowesproject.databinding.FragmentWeatherListElementBinding
import com.example.lowesproject.model.LowesWeather
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.math.round

class WeatherRVAdapter (private val weatherList : LowesWeather) : RecyclerView.Adapter<WeatherRVAdapter.WeatherViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {

        val binding: FragmentWeatherListElementBinding = FragmentWeatherListElementBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WeatherViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return weatherList.list.size
    }

    override fun onBindViewHolder(holder: WeatherRVAdapter.WeatherViewHolder, position: Int) {
        if (weatherList != null) {
            if (weatherList.list.isNotEmpty()) {
                weatherList.list[position].also { list ->
                    holder.setWeather(list.dt, list.main.temp, list.weather[0]?.let{ it.description} ?: "")
                }
            }
        }
    }

    class WeatherViewHolder(private val binding: FragmentWeatherListElementBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setWeather(dt : Long, temp : Double, desc : String) {
            val instant = Instant.ofEpochSecond(dt)
            val formatter = DateTimeFormatter.ofPattern("E : hh:MMa")
            val date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
            Log.i(
                "TAG -> WeatherRVAdapter",
                "setWeather: " + formatter.format(date) + " - High Temp: " + temp
            )
            binding.tvDate.text = formatter.format(date)
            binding.tvTemp.text = round(temp).toInt().toString() + (176).toChar() + 'F'
            binding.tvConditions.text = desc.trim()

        }

    }
}

