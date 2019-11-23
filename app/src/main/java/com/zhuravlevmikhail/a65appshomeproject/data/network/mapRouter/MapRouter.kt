package com.zhuravlevmikhail.a65appshomeproject.data.network.mapRouter

import com.google.android.gms.maps.model.LatLng
import io.reactivex.Single

interface MapRouter {
    fun getRoute(from: LatLng, to: LatLng) : Single<List<LatLng>>
}