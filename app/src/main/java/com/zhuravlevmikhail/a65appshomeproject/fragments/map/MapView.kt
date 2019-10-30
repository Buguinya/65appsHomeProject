package com.zhuravlevmikhail.a65appshomeproject.fragments.map

import com.google.android.gms.maps.model.LatLng
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface MapView : MvpView {
    fun addMarker(latLng: LatLng)
    fun showError(error: Int)
    fun showError(error: String)
    fun moveCameraToPosition(latLng: LatLng)
    fun addMarker(latLng: LatLng, title: String)
}