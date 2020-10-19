package com.suadahaji.weatherapp.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.suadahaji.weatherapp.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}