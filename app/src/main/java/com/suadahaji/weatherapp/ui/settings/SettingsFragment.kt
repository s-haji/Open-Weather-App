package com.suadahaji.weatherapp.ui.settings

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.suadahaji.weatherapp.R
import com.suadahaji.weatherapp.di.Injectable
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class SettingsFragment : PreferenceFragmentCompat(), Injectable, HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: SettingsViewModel by viewModels { viewModelFactory }


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val preferences = PreferenceManager.getDefaultSharedPreferences(activity)
        val listPreference = findPreference<ListPreference>("theme")
        listPreference!!.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener() { _, newValue ->
                val teme = newValue as String
                val value = Integer.parseInt(teme)

                when (value) {
                    AppCompatDelegate.MODE_NIGHT_YES -> AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES
                    )
                    AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                    )
                    AppCompatDelegate.MODE_NIGHT_NO -> AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO
                    )

                }
                preferences.edit().putString("theme", value.toString()).apply()
                true
            }
        val resetDataPreference = findPreference<Preference>("reset_data")

        resetDataPreference!!.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            val alertDialog: AlertDialog? = activity?.let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setPositiveButton(
                        R.string.reset_data_accept
                    ) { _, _ ->
                        viewModel.deleteAllCities()
                        Toast.makeText(activity, "Bookmarks Deleted", Toast.LENGTH_SHORT).show()
                    }
                    setNegativeButton(R.string.cancel) { dialog, _ ->
                        dialog.dismiss()
                    }
                    setTitle(getString(R.string.reset_data_title))
                    setMessage(getString(R.string.reset_data))
                }
                builder.create()
            }
            alertDialog?.show()
            true
        }

        getWebView(getString(R.string.preference_key_help), getString(R.string.help))
        getWebView(getString(R.string.preference_key_privacy),getString(R.string.privacy))
        getWebView(getString(R.string.preference_key_open_source),getString(R.string.open_source))
        getWebView(getString(R.string.preference_key_about),getString(R.string.about))
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    private fun getWebView(key: String, title: String) {
        findPreference<Preference>(key)!!.onPreferenceClickListener =
            Preference.OnPreferenceClickListener {
                val action = SettingsFragmentDirections.actionSettingsFragmentToWebViewFragment(key, title)
                findNavController().navigate(action)
                true
            }
    }
}