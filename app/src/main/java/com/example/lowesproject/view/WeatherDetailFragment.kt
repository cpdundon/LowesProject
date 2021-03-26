package com.example.lowesproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lowesproject.adapter.WeatherRVAdapter
import com.example.lowesproject.databinding.FragmentWeatherDetailBinding
import com.example.lowesproject.databinding.FragmentWeatherListBinding
import com.example.lowesproject.enum.IntConstants
import com.example.lowesproject.model.LowesWeather
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlin.math.roundToInt

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WeatherDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WeatherDetailFragment : Fragment() {
    private lateinit var binding: FragmentWeatherDetailBinding
    private val args: WeatherDetailFragmentArgs by navArgs()
    private lateinit var weather: LowesWeather

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

        weather = args.lowesWeather
        val position = args.position

        binding.apply {
            tvCity.text = weather.city.name.toString()
            weather.list[position].also {
                tvTemp.text = it.main.temp.roundToInt().toString()+ (176).toChar() + 'F'
                tvFeelsLike.text = it.main.feels_like.roundToInt().toString()+ (176).toChar() + 'F'
                tvShortDesc.text = it.weather[IntConstants.WEATHER_LIST_ROOT.value].main
                tvLongDesc.text = it.weather[IntConstants.WEATHER_LIST_ROOT.value].description
            }
        }
        setListeners()
    }

    private fun setListeners() {
        binding.btnBack.setOnClickListener(View.OnClickListener {
            val action = WeatherDetailFragmentDirections.actionWeatherDetailFragmentToWeatherListFragment(weather)
            this.findNavController().navigate(action)
        })
    }

}