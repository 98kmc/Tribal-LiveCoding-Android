package com.kmc.android_views_skeleton.infrastructure

import android.os.Parcelable

interface ViewModel<T> : Parcelable {

    fun onEvent(event: T)
}