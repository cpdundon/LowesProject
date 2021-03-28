package com.example.lowesproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lowesproject.adapter.WeatherRVAdapter
import com.example.lowesproject.databinding.FragmentWeatherListBinding
import com.example.lowesproject.model.LowesWeather
import com.example.lowesproject.viewmodel.WeatherViewModel

class WeatherListFragment : Fragment() {
    private lateinit var binding: FragmentWeatherListBinding
    private lateinit var weather: LowesWeather
    private val viewModel: WeatherViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) = FragmentWeatherListBinding.inflate(
            inflater,
            container,
            false
    ).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayoutManager = LinearLayoutManager(this.context)
        binding.rvWeatherList.layoutManager = linearLayoutManager

        setListeners()

        viewModel.weather.value?.let { wrapper ->
            wrapper.lowesWeather?.let {
                weather = it
                binding.tvCity.text = weather.city.name
                binding.rvWeatherList.adapter = WeatherRVAdapter(weather)
            }
        }
    }

    private fun setListeners() {
        binding.btnBack.setOnClickListener(View.OnClickListener {
            this.findNavController().navigateUp()
        })
    }
}