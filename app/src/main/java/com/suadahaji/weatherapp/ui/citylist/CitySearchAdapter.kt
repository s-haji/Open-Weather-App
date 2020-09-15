package com.suadahaji.weatherapp.ui.citylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.suadahaji.weatherapp.R
import com.suadahaji.weatherapp.data.api.WeatherResponse

class CitySearchAdapter(
    private var listener: ItemClickListener,
    private var cities: List<WeatherResponse>
) : RecyclerView.Adapter<CitySearchAdapter.CityViewHolder>() {
    interface ItemClickListener {
        fun onCityClicked(cityId: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_city_search, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.cityName.setText(cities[position].name + ", " + cities[position].sys.country)
        holder.weatherDescription.setText(cities[position].weather[0].description)

        holder.itemView.setOnClickListener {
            listener.onCityClicked(cities[position].id)
        }
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityName = itemView.findViewById<TextView>(R.id.cityName)
        val weatherDescription = itemView.findViewById<TextView>(R.id.weatherDescription)
    }

}