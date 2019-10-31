package com.zhuravlevmikhail.a65appshomeproject.data.retrofit

import io.reactivex.Single

interface GeoDecoder {
    fun geoDecodeLocation(lngLat: String, key: String) : Single<String>
}