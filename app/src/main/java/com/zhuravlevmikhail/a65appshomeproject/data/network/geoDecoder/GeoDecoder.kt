package com.zhuravlevmikhail.a65appshomeproject.data.network.geoDecoder

import io.reactivex.Single

interface GeoDecoder {
    fun geoDecodeLocation(lngLat: String, key: String) : Single<String>
}