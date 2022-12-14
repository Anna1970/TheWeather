package ru.naa.theweather.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import ru.naa.theweather.R
import ru.naa.theweather.adapter.CityListAdapter
import ru.naa.theweather.api.CityResponse
import ru.naa.theweather.databinding.FragmentCityListBinding
import ru.naa.theweather.model.WeatherListViewModel
import ru.naa.theweather.room.CityEntity

class CityListFragment : Fragment() {

    private var _binding: FragmentCityListBinding? = null

    private lateinit var viewModel: WeatherListViewModel

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCityListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[WeatherListViewModel::class.java]
        return binding.root
    }

    override  fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CityListAdapter()
        adapter.itemClick {
            val bundle = Bundle()
            bundle.putString("CityName",it.name)
            bundle.putString("CityKey", it.key)
            Navigation.findNavController(binding.root).navigate(R.id.action_CityListFragment_to_WeatherFragment,bundle)
        }

        viewModel.getAll()?.observe(requireActivity()){
            Log.d("-=WEATHER=-","call get dao")
            if (it.isEmpty()){
                val list = viewModel.gatApi()  //initCities()
                list.value?.forEach { city ->
                    viewModel.insert(city)
                }
            }
            adapter.items = it
        }

        //Обновляет данные с сервера погоды
        binding.refreshCityBtn.setOnClickListener {
            Log.d("-=WEATHER=-","call get api")
            val list = viewModel.gatApi()//initCities()
            if (list.value != null) {
                Log.d("-=WEATHER=-","get api not null")
                viewModel
                list.value?.forEach { city ->
                    viewModel.insert(city)
                }
                adapter.items = list.value!!
            }
            this.onViewCreated(it,savedInstanceState)
        }

        binding.rvCityList.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /*fun initCities(): List<CityEntity> {
        var citiesIns = viewModel.get//emulateApi()

        return citiesIns
    }*/

    private  fun  emulateApi():  List<CityEntity> {
        val result = arrayListOf<CityEntity>()
        result.add(CityEntity("288689" , "Москва", "City"))
        result.add(CityEntity("789654" , "Ростов", "City"))
        result.add(CityEntity("123456" , "Краснодар", "City"))
        result.add(CityEntity("456123" , "Ялта", "City"))
        result.add(CityEntity("789456" , "Сочи", "City"))
        result.add(CityEntity("789459" , "Калининград", "City"))
        result.add(CityEntity("789486" , "Севастополь", "City"))
        result.add(CityEntity("789286" , "Владивосток", "City"))
        result.add(CityEntity("783486" , "Красноярск", "City"))
        result.add(CityEntity("749486" , "Хабаровск", "City"))
        result.add(CityEntity("789586" , "Волгоград", "City"))
        result.add(CityEntity("782486" , "Омск", "City"))
        result.add(CityEntity("779486" , "Симферополь", "City"))
        result.add(CityEntity("729486" , "Керчь", "City"))
        result.add(CityEntity("719486" , "Ярославль", "City"))
        return result
    }
}