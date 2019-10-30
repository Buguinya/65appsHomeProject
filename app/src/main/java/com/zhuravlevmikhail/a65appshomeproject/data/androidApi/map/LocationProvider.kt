package com.zhuravlevmikhail.a65appshomeproject.data.androidApi.map

import com.google.android.gms.maps.model.LatLng
import io.reactivex.Single

interface LocationProvider {
    fun getCurrentUserLocation(): Single<LatLng>
}