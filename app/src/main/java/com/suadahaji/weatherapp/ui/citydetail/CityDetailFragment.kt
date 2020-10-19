package com.suadahaji.weatherapp.ui.citydetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.preference.PreferenceManager
import com.suadahaji.weatherapp.databinding.FragmentCityDetailBinding
import com.suadahaji.weatherapp.di.Injectable
import com.suadahaji.weatherapp.util.UNITS
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.fragment_city_detail.*
import javax.inject.Inject

class CityDetailFragment : Fragment(), Injectable, HasAndroidInjector {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    private val viewModel: CityDetailViewModel by viewModels { viewModelFactory }

    private val args: CityDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCityDetailBinding.inflate(inflater, container, false)
        context ?: return binding.root
        binding.cityViewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cityId = args.cityId
        val preference = PreferenceManager.getDefaultSharedPreferences(context)
        val units = preference.getString(UNITS, "metric")
        viewModel.setQuery(cityId, units)
        viewModel.fetchCityWeather()

        viewModel.forecasts.observe(viewLifecycleOwner, Observer {
            val adapter = CityForecastAdapter(it.drop(1))
            forecastRecyclerView.adapter = adapter
        })
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}