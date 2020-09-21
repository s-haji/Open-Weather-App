package com.suadahaji.weatherapp.ui.citylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suadahaji.weatherapp.data.api.WeatherResponse
import com.suadahaji.weatherapp.data.models.CityModel
import com.suadahaji.weatherapp.databinding.ItemCitySearchBinding
import java.util.*

class CitySearchAdapter(
    private var listener: ItemClickListener,
    private var cities: List<WeatherResponse>
) : RecyclerView.Adapter<CitySearchAdapter.CityViewHolder>() {
    interface ItemClickListener {
        fun onItemClicked(cityModel: CityModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCitySearchBinding.inflate(layoutInflater, parent, false)
        return CityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(cities[position])

        holder.binding.root.setOnClickListener {
            val city = CityModel(
                Date(System.currentTimeMillis()),
                cities[position].dt,
                cities[position].id,
                cities[position].name,
                cities[position].weather[0].description,
                cities[position].weather[0].icon,
                cities[position].main.temp,
                cities[position].sys.country,
                cities[position].sys.sunrise,
                cities[position].sys.sunset
            )
            listener.onItemClicked(city)
        }
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    class CityViewHolder(val binding: ItemCitySearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: WeatherResponse) {
            binding.weatherResponse = weather
        }
    }
}