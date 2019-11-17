package com.zhuravlevmikhail.a65appshomeproject.fragments.mapGeneral

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker

class RoutableMap(private val map : GoogleMap,
                  private val listener: RouteRequireListener) {

    private lateinit var origin : LatLng
    private lateinit var destination : LatLng

    private var isOriginSelected = false

    fun onMapReady() {
        map.setOnMarkerClickListener {marker ->
            if (isOriginSelected) {
                destination = marker.position
                listener.onTwoPointsSelected(origin, destination)
            } else {
                origin = marker.position
                isOriginSelected = true
            }
            return@setOnMarkerClickListener true
        }
    }
}