package com.suadahaji.weatherapp.ui.citylist

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.suadahaji.weatherapp.R
import com.suadahaji.weatherapp.data.models.CityModel
import com.suadahaji.weatherapp.di.Injectable
import com.suadahaji.weatherapp.ui.settings.SettingsActivity
import com.suadahaji.weatherapp.util.UNITS
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.fragment_city_list.*
import javax.inject.Inject

class CityListFragment : Fragment(), Injectable, HasAndroidInjector,
    CityListAdapter.ItemClickListener, SearchView.OnQueryTextListener,
    CitySearchAdapter.ItemClickListener {

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


        fab.setOnClickListener {
            findNavController().navigate(R.id.action_CityListFragment_to_AddCityFragment)
        }

        viewModel.fetchAllCities()
        viewModel.cities.observe(viewLifecycleOwner, Observer {
            val adapter = CityListAdapter(this, it)

            val dividerItemDecoration = DividerItemDecoration(
                cityListRecyclerview.context,
                LinearLayoutManager.VERTICAL
            )
            cityListRecyclerview.addItemDecoration(dividerItemDecoration)
            cityListRecyclerview.adapter = adapter
        })
        citySearchView.setOnQueryTextListener(this)
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

    override fun onQueryTextSubmit(p0: String): Boolean {
        if (p0.isNotEmpty()) {
            val preference = PreferenceManager.getDefaultSharedPreferences(context)
            val units = preference.getString(UNITS,  getString(R.string.unit_default))
            viewModel.setQuery(p0, units)
            viewModel.searchCities()
            viewModel.searchCities.observe(viewLifecycleOwner, Observer {
                searchRecyclerView.adapter = CitySearchAdapter(this, it)
                if (it.isNotEmpty()) {
                    emptySearch.visibility = View.GONE
                    searchRecyclerView.visibility = View.VISIBLE
                } else {
                    emptySearch.visibility = View.VISIBLE
                    searchRecyclerView.visibility = View.GONE
                }
            })
        }
        return false
    }

    override fun onQueryTextChange(p0: String): Boolean {
        if (p0.isEmpty()) {
            searchRecyclerView.adapter = CitySearchAdapter(this, emptyList())
            emptySearch.visibility = View.GONE
            searchRecyclerView.visibility = View.GONE
        }
        return false
    }

    override fun onItemClicked(cityModel: CityModel) {
        viewModel.getCity(cityModel)
        viewModel.addCity()
        findNavController().navigate(
            CityListFragmentDirections.actionCityListFragmentToCityDetailFragment(
                cityModel.id
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                startActivity(Intent(activity, SettingsActivity::class.java))
                true
            }
            R.id.help -> {
                Toast.makeText(activity, "Help", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}