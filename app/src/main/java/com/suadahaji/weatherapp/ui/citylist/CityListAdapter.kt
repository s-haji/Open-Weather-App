package com.suadahaji.weatherapp.ui.citylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.suadahaji.weatherapp.R
import com.suadahaji.weatherapp.data.models.CityModel

class CityListAdapter(
    private var listener: ItemClickListener,
    private var cities: List<CityModel>
) : RecyclerView.Adapter<CityListAdapter.CityViewHolder>() {
    interface ItemClickListener {
        fun onCityClicked(city: CityModel)
        fun onCityLongClicked(city: CityModel): Boolean
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.cityName.setText(cities[position].name)
        holder.weatherDescription.setText(cities[position].description)

        holder.itemView.setOnClickListener {
            listener.onCityClicked(cities[position])
        }
        holder.itemView.setOnLongClickListener { v ->
            v.isSelected = true
            listener.onCityLongClicked(cities[position])
        }
    }

    override fun getItemCount(): Int {
        return cities.size
    }

//    fun addCity(cityModel: CityModel){
//        cities.add(cityModel)
//        notifyItemInserted(cities.size -1)
//    }


    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityName = itemView.findViewById<TextView>(R.id.cityName)
        val weatherDescription = itemView.findViewById<TextView>(R.id.weatherDescription)
        val cityTemp = itemView.findViewById<TextView>(R.id.cityTemp)
    }

}