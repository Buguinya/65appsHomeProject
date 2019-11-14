package com.zhuravlevmikhail.a65appshomeproject.core.baseMap

import com.google.android.gms.maps.model.LatLng

interface BaseMapView {
    fun showError(error: Int)
    fun showError(error: String)
    fun moveCameraToPosition(latLng: LatLng)
    fun addMarker(latLng: LatLng)
    fun addMarker(latLng: LatLng, title: String)
}