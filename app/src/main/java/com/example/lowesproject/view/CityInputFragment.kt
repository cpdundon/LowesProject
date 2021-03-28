package com.example.lowesproject.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.example.lowesproject.databinding.FragmentCityInputBinding
import com.example.lowesproject.enum.StringConstants
import com.example.lowesproject.model.LowesWeather
import com.example.lowesproject.model.LowesWeatherWrapper
import com.example.lowesproject.viewmodel.WeatherViewModel
import com.google.android.material.tabs.TabLayout
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.parcelize.Parcelize
import java.lang.reflect.InvocationTargetException

class CityInputFragment : Fragment() {
    companion object {
        val TAG = "CityInputFragment"
    }
    private lateinit var binding: FragmentCityInputBinding
    private val viewModel: WeatherViewModel by activityViewModels()
    private var newClick = true // Need this to capture viewModel re-fire on re-entering the fragment

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
                if (newClick) {
                    val httpCode = it.httpCode

                    if (httpCode == 200) {
                        proceedToRV(it.lowesWeather)
                    } else {
                        errorDisplay(it.httpCode, it.message)
                    }
                    newClick = false
                }
            })
    }

    private fun errorDisplay(httpCode: Int, message: String) {
        val dispText = "There has been an error with the input.  Please check the city name for spelling.  It may not be in our database. \n" +
                "http Error Code: $httpCode. \n" +
                "Error Message: $message"

        binding.etError.text = dispText
        binding.etError.visibility = View.VISIBLE
    }

    private fun setUpListeners() {
        binding.btnFetch.setOnClickListener(View.OnClickListener {
            newClick = true
            val city = binding.etCity.text.toString().trim()
            viewModel.fetchWeather(city, StringConstants.UNITS.value, StringConstants.APP_ID.value)
        })
    }

    private fun proceedToRV (lowesWeather : LowesWeather?) {
        lowesWeather?.let {
            val action = CityInputFragmentDirections.actionCityInputFragmentToWeatherListFragment()
            this.findNavController().navigate(action)
        }
    }
}

