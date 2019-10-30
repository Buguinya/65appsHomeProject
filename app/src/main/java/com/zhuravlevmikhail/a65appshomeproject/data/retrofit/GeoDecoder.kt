package com.zhuravlevmikhail.a65appshomeproject.data.retrofit

import com.google.android.gms.maps.model.LatLng
import io.reactivex.Single

interface GeoDecoder {
    fun geoDecodeLocation(latLng: LatLng, key: String) : Single<String>
}