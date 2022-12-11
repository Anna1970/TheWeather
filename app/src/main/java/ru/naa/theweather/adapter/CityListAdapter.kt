package ru.naa.theweather.adapter

import android.content.ClipData.Item
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import okhttp3.internal.http2.Http2Connection.Listener
import ru.naa.theweather.R
import ru.naa.theweather.databinding.ItemCityBinding
import ru.naa.theweather.model.CityData

class CityListAdapter() : RecyclerView.Adapter<CityListAdapter.ViewHolder>() {

    var items: List<CityData> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

   var itemClick: (CityData) -> Unit = {}
    fun itemClick(listener: (CityData) -> Unit){
        itemClick = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_city, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.cityData = items[position]
        holder.itemView.setOnClickListener {
            itemClick(items[position])
        }

        holder.binding.layoutCity.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("CityName", items[position].name)
            bundle.putString("CityKey", items[position].key)

            Navigation.findNavController(it).navigate(R.id.action_CityListFragment_to_WeatherFragment,bundle)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding = ItemCityBinding.bind(view)
    }
}