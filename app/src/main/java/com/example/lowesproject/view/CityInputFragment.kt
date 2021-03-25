package com.example.lowesproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.example.lowesproject.databinding.FragmentCityInputBinding
import com.example.lowesproject.enum.StringConstants
import com.example.lowesproject.model.LowesWeather
import com.example.lowesproject.model.LowesWeatherWrapper
import com.example.lowesproject.viewmodel.WeatherViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.parcelize.Parcelize

class CityInputFragment : Fragment() {
    private lateinit var binding: FragmentCityInputBinding
    private val viewModel = WeatherViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCityInputBinding.inflate(
        inflater,
        container,
        false
    ).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()
        setUpListeners()

    }

    private fun setUpObservers() {
        viewModel.weather.observe(viewLifecycleOwner,
            Observer<LowesWeatherWrapper> {
                val httpCode = it.httpCode

                if (httpCode == 200) {
                    proceedToRV(it.lowesWeather)
                } else {
//                    errorDisplay(it.httpCode, it.message)
                }
            })
    }

    private fun setUpListeners() {
        binding.btnFetch.setOnClickListener(View.OnClickListener {
            val city = binding.etCity.text.toString().trim()
            viewModel.fetchWeather(city, StringConstants.UNITS.value, StringConstants.APP_ID.value)
        })
    }

    private fun proceedToRV (lowesWeather : LowesWeather?) {
        lowesWeather?.let {
            val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()

            val adapt = moshi.adapter(LowesWeather::class.java)
            val handOff = adapt.toJson(it)

            val action = CityInputFragmentDirections.actionCityInputFragmentToWeatherListFragment(handOff)
            this.findNavController().navigate(action)
        }
    }
}

