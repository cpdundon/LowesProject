package com.example.lowesproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.lowesproject.R
import com.example.lowesproject.databinding.ListElementWeatherBinding
import com.example.lowesproject.enum.IntConstants
import com.example.lowesproject.model.LowesWeather
import com.example.lowesproject.view.WeatherListFragmentDirections
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.math.round

class WeatherRVAdapter(private val weatherList: LowesWeather) : RecyclerView.Adapter<WeatherRVAdapter.WeatherViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {

        val binding: ListElementWeatherBinding = ListElementWeatherBinding.inflate(
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

    class WeatherViewHolder(private val binding: ListElementWeatherBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun setWeather(weatherList: LowesWeather, position: Int) {
            if (weatherList.list.isNotEmpty()) {
                weatherList.list[position].also { list ->
                    val dt = list.dt
                    val temp = list.main.temp
                    val weatherTxt = list.weather[IntConstants.WEATHER_LIST_ROOT.value].description

                    val instant = Instant.ofEpochSecond(dt)
                    val formatter = DateTimeFormatter.ofPattern("E - ha")
                    val date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())

                    val strFormat = binding.root.context.getString(R.string.base_temp_disp)
                    binding.tvTemp.text = String.format(strFormat, round(temp).toInt(), (176).toChar())

                    binding.tvDate.text = formatter.format(date)
                    binding.tvConditions.text = weatherTxt.trim()
                }
            }
            setListeners(position)
        }

        private fun setListeners(position: Int) {
            binding.clWeatherElement.setOnClickListener(View.OnClickListener {
                val action = WeatherListFragmentDirections.actionWeatherListFragmentToWeatherDetailFragment(position)
                it.findNavController().navigate(action)
            })
        }
    }
}

