package com.suadahaji.weatherapp.ui.settings

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.suadahaji.weatherapp.R
import com.suadahaji.weatherapp.di.Injectable
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.fragment_webview.*
import javax.inject.Inject


class WebViewFragment : Fragment(), Injectable, HasAndroidInjector {

    private val args: WebViewFragmentArgs by navArgs()

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_webview, container, false)
        setHasOptionsMenu(true)
        return view
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.runOnUiThread {
            webView.settings.javaScriptEnabled = true
            webView.settings.domStorageEnabled = true
            webView.settings.builtInZoomControls = true

            webView.settings.javaScriptCanOpenWindowsAutomatically = true
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                webView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE;
            }
            webView.webViewClient = object : WebViewClient() {}
            webView.loadUrl(getUrl())
        }
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    private fun getUrl(): String {
        return when (args.preferenceId) {
            getString(R.string.preference_key_privacy) -> getString(R.string.privacy_url)

            getString(R.string.preference_key_open_source) -> getString(R.string.open_source_url)

            else -> getString(R.string.help_web_url)
        }
    }
}