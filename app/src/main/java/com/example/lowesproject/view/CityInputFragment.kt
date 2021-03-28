package com.example.lowesproject.view

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.lowesproject.R
import com.example.lowesproject.databinding.FragmentCityInputBinding
import com.example.lowesproject.enum.StringConstants
import com.example.lowesproject.model.LowesWeather
import com.example.lowesproject.viewmodel.WeatherViewModel

class CityInputFragment : Fragment() {
    private lateinit var binding: FragmentCityInputBinding
    private val viewModel: WeatherViewModel by activityViewModels()
    private var newClick = true // Need this to prevent viewModel re-fire on re-entering the fragment

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
                {
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
        val strFormat = getString(R.string.restError)

        binding.etError.text = String.format(strFormat, httpCode, message)
        binding.etError.visibility = View.VISIBLE
    }

    private fun setUpListeners() {
        binding.btnFetch.setOnClickListener {
            navigate()
        }

        binding.etCity.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                navigate()
                true
            } else {
                false
            }
        })
    }

    private fun navigate() {
        newClick = true
        val city = binding.etCity.text.toString().trim()
        viewModel.fetchWeather(city, StringConstants.UNITS.value, StringConstants.APP_ID.value)
    }

    private fun proceedToRV(lowesWeather: LowesWeather?) {
        lowesWeather?.let {
            val action = CityInputFragmentDirections.actionCityInputFragmentToWeatherListFragment()
            this.findNavController().navigate(action)
        }
    }
}

