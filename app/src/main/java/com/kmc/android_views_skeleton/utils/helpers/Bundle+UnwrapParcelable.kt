package com.kmc.android_views_skeleton.utils.helpers

import android.os.Build
import android.os.Bundle

inline fun <reified T>Bundle.unwrapParcelable(name: String): T {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

        this.getParcelable(name, T::class.java)
            ?: throw IllegalStateException("The \'viewModel\' parameter was not found.")
    } else {
        @Suppress("DEPRECATION")
        this.getParcelable(name)
            ?: throw IllegalStateException("The \'viewModel\' parameter was not found.")
    }
}