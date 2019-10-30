package com.zhuravlevmikhail.a65appshomeproject.fragments.map

import com.google.android.gms.maps.model.LatLng
import moxy.MvpView

interface MapView : MvpView {
    fun addMarker(latLng: LatLng)
    fun showError(error: Int)
    fun showError(error: String)
    fun moveCameraToPosition(latLng: LatLng)
}