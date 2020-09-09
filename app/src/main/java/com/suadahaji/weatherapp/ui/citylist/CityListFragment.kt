package com.suadahaji.weatherapp.ui.citylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.suadahaji.weatherapp.R
import com.suadahaji.weatherapp.di.Injectable
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.fragment_city_list.*
import javax.inject.Inject

class CityListFragment : Fragment(), Injectable, HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CityListViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_city_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        fab.setOnClickListener {
            findNavController().navigate(R.id.action_CityListFragment_to_AddCityFragment)
        }

        viewModel.fetchAllCities()
        viewModel.cities.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it.size, Toast.LENGTH_SHORT).show()
        })
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}