package com.example.lowesproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.lowesproject.databinding.FragmentWeatherListElementBinding
import com.example.lowesproject.enum.IntConstants
import com.example.lowesproject.model.LowesWeather
import com.example.lowesproject.view.WeatherListFragmentDirections
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
        holder.setWeather(weatherList, position)
    }

    class WeatherViewHolder(private val binding: FragmentWeatherListElementBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setWeather(weatherList: LowesWeather, position: Int) {
            if (weatherList.list.isNotEmpty()) {
                weatherList.list[position].also { list ->
                    val dt = list.dt
                    val temp = list.main.temp
                    val weatherTxt = list.weather[IntConstants.WEATHER_LIST_ROOT.value]?.let{ it.description} ?: ""

                    val instant = Instant.ofEpochSecond(dt)
                    val formatter = DateTimeFormatter.ofPattern("E - ha")
                    val date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())

                    binding.tvDate.text = formatter.format(date)
                    binding.tvTemp.text = round(temp).toInt().toString() + (176).toChar() + 'F'
                    binding.tvConditions.text = weatherTxt.trim()
                }
            }
            setListeners(weatherList, position)
        }

        private fun setListeners (weatherList: LowesWeather, position: Int) {
            binding.clWeatherElement.setOnClickListener(View.OnClickListener {
                val action = WeatherListFragmentDirections.actionWeatherListFragmentToWeatherDetailFragment(position)
                it.findNavController().navigate(action)
            })
        }
    }
}

