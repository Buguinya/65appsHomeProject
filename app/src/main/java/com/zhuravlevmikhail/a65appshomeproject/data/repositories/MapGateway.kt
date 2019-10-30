package com.zhuravlevmikhail.a65appshomeproject.data.repositories

import com.google.android.gms.maps.model.LatLng
import com.zhuravlevmikhail.a65appshomeproject.data.api.map.MapProvider
import com.zhuravlevmikhail.a65appshomeproject.domain.map.MapRepository
import io.reactivex.Single

class MapGateway(private val mapProvider: MapProvider) : MapRepository {

    override fun getCurrentUserLocation(): Single<LatLng> {
        return mapProvider.getCurrentUserLocation()
    }
}