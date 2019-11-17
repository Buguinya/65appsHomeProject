package com.zhuravlevmikhail.a65appshomeproject.fragments.mapGeneral

import com.google.android.gms.maps.model.LatLng

interface RouteRequireListener {
    fun onTwoPointsSelected(first : LatLng, second : LatLng)
}