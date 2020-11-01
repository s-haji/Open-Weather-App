package com.suadahaji.weatherapp.ui.settings.help

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.suadahaji.weatherapp.R
import com.suadahaji.weatherapp.di.Injectable
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.fragment_help.*
import javax.inject.Inject


class HelpFragment : Fragment(), Injectable, HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_help, container, false)
        setHasOptionsMenu(true)
        return view
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = getString(R.string.web_url)

        activity?.runOnUiThread {
            webView.settings.javaScriptEnabled = true
            webView.settings.domStorageEnabled = true // Add this
            webView.settings.builtInZoomControls = true

            webView.settings.javaScriptCanOpenWindowsAutomatically = true
            webView.webViewClient = object : WebViewClient() {}
            webView.loadUrl(url)
        }
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}