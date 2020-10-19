package com.suadahaji.weatherapp.ui.addcity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.suadahaji.weatherapp.R
import com.suadahaji.weatherapp.di.Injectable
import com.suadahaji.weatherapp.util.TAG
import com.suadahaji.weatherapp.util.UNITS
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class AddCityFragment : Fragment(), Injectable, HasAndroidInjector, OnMapReadyCallback,
    GoogleMap.OnMapClickListener {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: AddCityViewModel by viewModels { viewModelFactory }

    private lateinit var map: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_city, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val sydney = LatLng(-34.0, 151.0)
        map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        val cameraPosition = CameraPosition.Builder().target(
            sydney
        ).zoom(2f).build()
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        map.setOnMapClickListener(this)
        map.uiSettings.isZoomControlsEnabled = true
    }

    override fun onMapClick(latLng: LatLng) {
        map.addMarker(MarkerOptions().position(latLng))

        val preference = PreferenceManager.getDefaultSharedPreferences(context)
        val units = preference.getString(UNITS, getString(R.string.unit_default))

        viewModel.setQuery(latLng.latitude.toString(), latLng.longitude.toString(), units)
        viewModel.fetchCityWeather()

        viewModel.weather.observe(viewLifecycleOwner, Observer {
            if (it.name.isNotEmpty()) {
                findNavController().navigate(
                    AddCityFragmentDirections.actionAddCityFragmentToCityDetailFragment(
                        it.id
                    )
                )
                Log.d(TAG, "onMapClick: $it")
            } else {
                Toast.makeText(activity, "Select a city", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}