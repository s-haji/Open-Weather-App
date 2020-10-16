package com.suadahaji.weatherapp.ui.citydetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suadahaji.weatherapp.data.models.extras.Forecast
import com.suadahaji.weatherapp.databinding.ItemDailyWeatherBinding

class CityForecastAdapter(
    private var forecasts: List<Forecast>
) : RecyclerView.Adapter<CityForecastAdapter.CityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemDailyWeatherBinding.inflate(layoutInflater, parent, false)
        return CityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        if (forecasts[position] == forecasts.first()) {
            forecasts[position].dt_txt = "Tomorrow"
        }

        holder.bind(forecasts[position])
    }

    override fun getItemCount(): Int = forecasts.size

    class CityViewHolder(val binding: ItemDailyWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(forecast: Forecast) {
            binding.cityForecast = forecast
        }
    }
}