package ru.naa.theweather.ui

import android.media.browse.MediaBrowser
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore.Images.Media
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import ru.naa.theweather.R
import ru.naa.theweather.databinding.FragmentWeatherBinding
import ru.naa.theweather.model.WeatherListViewModel
import java.io.File

class WeatherFragment : Fragment() {

    lateinit var binding: FragmentWeatherBinding
    lateinit var viewModel: WeatherListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherBinding.inflate(inflater)
        viewModel = ViewModelProvider(requireActivity())[WeatherListViewModel::class.java]
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val key = arguments?.getString("CityKey")
        val city = arguments?.getString("CityName")
        if (key != ""){
            key?.let {
                viewModel.getWeather(key)
            }
        }

        viewModel.itemWeather.observe(requireActivity()){
            if (it != null){
                binding.tvCityCurrent.text = city
                binding.tvDateCurrent.text = it.date
                binding.tvDescription.text = it.weathertext
                binding.tvTemperature.text = it.temperature.toString()
                binding.tvWind.text = it.wind
                Picasso.get()
                    //.load("file://app/src/main/res/drawable/ic_${it.icon}.png")
                    .load(getImage(it.icon))
                    .resize(100,80).error(R.drawable.c3)
                    .into(binding.icDescription)
            }
        }
    }

    fun getImage(icon:Int):Int{
        return when (icon) {
                1 -> R.drawable.ic_1
                2 -> R.drawable.ic_2
                3 -> R.drawable.ic_3
                4 -> R.drawable.ic_4
                5 -> R.drawable.ic_5
                6 -> R.drawable.ic_6
                7 -> R.drawable.ic_7
                8 -> R.drawable.ic_8
                12 -> R.drawable.ic_12
                14 -> R.drawable.ic_14
                15 -> R.drawable.ic_15
                17 -> R.drawable.ic_17
                else -> R.drawable.ic_weather_3
            }
    }
}