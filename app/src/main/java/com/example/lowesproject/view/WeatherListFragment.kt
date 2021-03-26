package com.example.lowesproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lowesproject.adapter.WeatherRVAdapter
import com.example.lowesproject.databinding.FragmentWeatherListBinding
import com.example.lowesproject.databinding.FragmentWeatherListElementBinding
import com.example.lowesproject.enum.StringConstants
import com.example.lowesproject.model.LowesWeather
import com.example.lowesproject.viewmodel.WeatherViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class WeatherListFragment : Fragment() {
    private lateinit var binding: FragmentWeatherListBinding
    private lateinit var handOff: LowesWeather
    private val args: WeatherListFragmentArgs by navArgs()

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

        setGridLayoutMgr(false)
        setListeners()

        handOff = args.weatherList
        binding.tvCity.text = handOff.city.name
        binding.rvWeatherList.adapter = WeatherRVAdapter(handOff)
    }

    private fun setListeners() {
        binding.btnBack.setOnClickListener(View.OnClickListener {
            //parentFragmentManager.beginTransaction().remove(this).commitNowAllowingStateLoss()
            val action = WeatherListFragmentDirections.actionWeatherListFragmentToCityInputFragment(handOff.city.name)
            this.findNavController().navigate(action)
        })

    }

    private fun setGridLayoutMgr(gridToggle : Boolean) {
        if (gridToggle) {
            val gridLayoutManager = GridLayoutManager(this.context, 3)
            binding.rvWeatherList.layoutManager = gridLayoutManager
        } else {
            val linearLayoutManager = LinearLayoutManager(this.context)
            binding.rvWeatherList.layoutManager = linearLayoutManager
        }
    }

}