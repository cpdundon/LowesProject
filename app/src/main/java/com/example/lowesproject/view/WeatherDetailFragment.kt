package com.example.lowesproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lowesproject.R
import com.example.lowesproject.databinding.FragmentWeatherDetailBinding
import com.example.lowesproject.enum.IntConstants
import com.example.lowesproject.model.LowesWeather
import com.example.lowesproject.viewmodel.WeatherViewModel
import kotlin.math.roundToInt

class WeatherDetailFragment : Fragment() {
    private lateinit var binding: FragmentWeatherDetailBinding
    private val args: WeatherDetailFragmentArgs by navArgs()
    private lateinit var weather: LowesWeather
    private val viewModel: WeatherViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) = FragmentWeatherDetailBinding.inflate(
            inflater,
            container,
            false
    ).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.weather.value?.let { wrapper ->
            wrapper.lowesWeather?.let {
                weather = it
                writeOutInfo(args.position)
            }
        }
        setListeners()
    }

    private fun writeOutInfo(position: Int) {
        val strFormatT = getString(R.string.base_temp_disp)
        val strFormatFLT = getString(R.string.feels_like_temp_disp)

        binding.apply {
            tvCity.text = weather.city.name.toString()
            weather.list[position].also {
                tvTemp.text = String.format(strFormatT, it.main.temp.roundToInt(), (176).toChar())
                tvFeelsLike.text = String.format(strFormatFLT, it.main.feels_like.roundToInt(), (176).toChar())
                tvShortDesc.text = it.weather[IntConstants.WEATHER_LIST_ROOT.value].main
                tvLongDesc.text = it.weather[IntConstants.WEATHER_LIST_ROOT.value].description
            }
        }
    }

    private fun setListeners() {
        binding.btnBack.setOnClickListener(View.OnClickListener {
            this.findNavController().navigateUp()
        })
    }

}