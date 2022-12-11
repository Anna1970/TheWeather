package ru.naa.theweather.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.naa.theweather.R
import ru.naa.theweather.adapter.CityListAdapter
import ru.naa.theweather.databinding.FragmentCityListBinding
import ru.naa.theweather.model.CityListViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CityListFragment : Fragment() {

    private var _binding: FragmentCityListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCityListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CityListAdapter()
        binding.rvCityList.adapter = adapter

        val viewModel = ViewModelProvider(requireActivity())[CityListViewModel::class.java]

        viewModel.getAll().observe(requireActivity()) {
            if ( it != null) { adapter.items = it }
        }

        //Обновляет данные с сервера
        binding.refreshCityBtn.setOnClickListener {
            this.onViewCreated(it,savedInstanceState)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}