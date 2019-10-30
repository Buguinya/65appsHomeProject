package com.zhuravlevmikhail.a65appshomeproject.data.api.map

import com.google.android.gms.maps.model.LatLng
import io.reactivex.Single

interface MapProvider {
    fun getCurrentUserLocation(): Single<LatLng>
}