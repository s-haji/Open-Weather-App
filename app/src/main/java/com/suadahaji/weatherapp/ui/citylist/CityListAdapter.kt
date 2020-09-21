package com.suadahaji.weatherapp.ui.citylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suadahaji.weatherapp.data.models.CityModel
import com.suadahaji.weatherapp.databinding.ItemCityBinding

class CityListAdapter(
    private var listener: ItemClickListener,
    private var cities: List<CityModel>
) : RecyclerView.Adapter<CityListAdapter.CityViewHolder>() {
    interface ItemClickListener {
        fun onCityClicked(city: CityModel)
        fun onCityLongClicked(city: CityModel): Boolean
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCityBinding.inflate(layoutInflater, parent, false)
        return CityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(cities[position])
        holder.binding.root.setOnClickListener {
            listener.onCityClicked(cities[position])
        }
        holder.binding.root.setOnLongClickListener { v ->
            v.isSelected = true
            listener.onCityLongClicked(cities[position])
        }
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    class CityViewHolder(val binding: ItemCityBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(city: CityModel) {
            binding.cityModel = city
        }
    }
}