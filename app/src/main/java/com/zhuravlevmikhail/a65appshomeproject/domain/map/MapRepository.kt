package com.zhuravlevmikhail.a65appshomeproject.domain.map

import com.google.android.gms.maps.model.LatLng
import io.reactivex.Single

interface MapRepository {

    fun getCurrentUserLocation() : Single<LatLng>
    fun geoDecodeLocation(latLng: LatLng, key: String): Single<String>
}