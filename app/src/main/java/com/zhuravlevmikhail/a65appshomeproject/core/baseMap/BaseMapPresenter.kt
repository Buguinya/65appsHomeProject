package com.zhuravlevmikhail.a65appshomeproject.core.baseMap

import com.google.android.gms.maps.model.LatLng

interface BaseMapPresenter {
    fun onMapClicked(latLng: LatLng)
    fun onMapCreated()
}