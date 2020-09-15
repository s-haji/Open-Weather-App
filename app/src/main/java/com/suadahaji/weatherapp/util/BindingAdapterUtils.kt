package com.suadahaji.weatherapp.util

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("checkVisibility")
fun View.checkVisibility(it: Boolean) {
    visibility = if (it) View.VISIBLE else View.GONE
}