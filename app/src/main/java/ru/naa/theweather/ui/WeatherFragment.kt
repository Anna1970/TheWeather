package ru.naa.theweather.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.naa.theweather.R
import ru.naa.theweather.databinding.FragmentWeatherBinding
import ru.naa.theweather.model.CityListViewModel
import ru.naa.theweather.model.WeatherViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(requireActivity())[WeatherViewModel::class.java]

        binding.tvCityCurrent.text = arguments?.getString("CityName")

        val key = arguments?.getString("CityKey")
        if (key != null){
            viewModel.getWeather(key.toInt()).observe(requireActivity()) {
                if ( it != null) {
                    binding.tvDateCurrent.text = it.date
                    binding.tvTemperature.text = it.temperature
                    binding.tvDescription.text = it.text
                    binding.tvWind.text = it.iconPhrase
                    binding.icDescription.setBackgroundResource(R.drawable.iic_weather_3)
                }
            }
        }





/*        binding.icBackup.setOnClickListener {
            findNavController().navigate(R.id.action_WeatherFragment_to_CityListFragment)
        }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}