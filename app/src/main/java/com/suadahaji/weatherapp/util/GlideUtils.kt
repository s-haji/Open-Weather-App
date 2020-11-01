package com.suadahaji.weatherapp.util

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import okhttp3.OkHttpClient
import java.io.InputStream
import javax.net.ssl.SSLContext

@GlideModule
open class GlideUtils : AppGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        enableTls12OnPreLollipop().let {
            registry.replace(
                GlideUrl::class.java,
                InputStream::class.java,
                OkHttpUrlLoader.Factory(it)
            )
        }
    }

    private fun enableTls12OnPreLollipop(): OkHttpClient {
        val unsafeTrustManager = createUnsafeTrustManager()
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, arrayOf(unsafeTrustManager), null)
        return OkHttpClient.Builder()
            .sslSocketFactory(sslContext.socketFactory, unsafeTrustManager)
            .hostnameVerifier { hostName, sslSession -> true }
            .build()
    }
}
