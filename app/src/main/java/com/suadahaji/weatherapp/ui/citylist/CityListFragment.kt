package com.suadahaji.weatherapp.ui.citylist

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.suadahaji.weatherapp.R
import com.suadahaji.weatherapp.data.api.WeatherResponse
import com.suadahaji.weatherapp.data.models.CityModel
import com.suadahaji.weatherapp.di.Injectable
import com.suadahaji.weatherapp.util.UNITS
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.fragment_city_list.*
import javax.inject.Inject

class CityListFragment : Fragment(), Injectable, HasAndroidInjector,
    CityListAdapter.ItemClickListener,
    CitySearchAdapter.ItemClickListener {

    private fun setAdapter(cities: List<WeatherResponse>) {
        searchRecyclerView.adapter = CitySearchAdapter(this, cities)
    }

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
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab.bringToFront()
        fab.setOnClickListener {
            findNavController().navigate(R.id.action_CityListFragment_to_AddCityFragment)
        }

        viewModel.fetchAllCities()
        viewModel.cities.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                emptyList.visibility = View.GONE
                cityListRecyclerview.visibility = View.VISIBLE
                val adapter = CityListAdapter(this, it)

                val dividerItemDecoration = DividerItemDecoration(
                    cityListRecyclerview.context,
                    LinearLayoutManager.VERTICAL
                )
                cityListRecyclerview.addItemDecoration(dividerItemDecoration)
                cityListRecyclerview.adapter = adapter
            } else {
                emptyList.visibility = View.VISIBLE
                cityListRecyclerview.visibility = View.GONE
            }
        })
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    override fun onCityClicked(city: CityModel) {
        findNavController().navigate(
            CityListFragmentDirections.actionCityListFragmentToCityDetailFragment(
                city.id
            )
        )
    }

    override fun onCityLongClicked(city: CityModel): Boolean {
        val alertDialog = activity?.let {
            val builder = AlertDialog.Builder(it, R.style.AlertDialogStyle)
            builder.apply {
                setMessage(R.string.delete_city)
                setPositiveButton(
                    R.string.delete
                ) { dialog, _ ->
                    viewModel.deleteCity(city)
                    Toast.makeText(activity, "City deleted", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                setNegativeButton(
                    R.string.cancel
                ) { dialog, _ ->
                    dialog.dismiss()
                }
            }
            builder.create()
        }
        alertDialog?.show()
        return true
    }

    override fun onItemClicked(cityModel: CityModel) {
        viewModel.getCity(cityModel)
        viewModel.addCity()
        findNavController().navigate(
            CityListFragmentDirections.actionCityListFragmentToCityDetailFragment(
                cityModel.id
            )
        )
        setAdapter(emptyList())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)

        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        }

        (menu.findItem(R.id.search).actionView as SearchView)
            .setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {
                        if (newText.isEmpty()) {
                            setAdapter(emptyList())
                            searchRecyclerView.visibility = View.GONE
                        }
                    }
                    return false
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        if (query.isNotEmpty()) {
                            val preference = PreferenceManager.getDefaultSharedPreferences(context)
                            val units =
                                preference.getString(UNITS, getString(R.string.unit_default))
                            viewModel.setQuery(query, units)
                            viewModel.searchCities()
                            viewModel.searchCities.observe(viewLifecycleOwner, Observer {
                                setAdapter(it)
                                if (it.isNotEmpty()) {
                                    searchRecyclerView.visibility = View.VISIBLE
                                    searchRecyclerView.bringToFront()
                                } else {
                                    Toast.makeText(
                                        activity,
                                        getString(R.string.error_fetch_city),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    searchRecyclerView.visibility = View.GONE
                                }
                            })
                        }
                    }
                    return false
                }
            })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                findNavController().navigate(R.id.action_CityListFragment_to_settingsFragment)
                true
            }
            R.id.help -> {
                val action = CityListFragmentDirections.actionCityListFragmentToWebViewFragment(
                    getString(R.string.preference_key_help),
                    getString(R.string.help)
                )
                findNavController().navigate(action)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}