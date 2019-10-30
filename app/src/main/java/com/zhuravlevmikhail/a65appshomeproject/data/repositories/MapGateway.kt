package com.zhuravlevmikhail.a65appshomeproject.data.repositories

import com.google.android.gms.maps.model.LatLng
import com.zhuravlevmikhail.a65appshomeproject.data.androidApi.map.LocationProvider
import com.zhuravlevmikhail.a65appshomeproject.data.retrofit.GeoDecoder
import com.zhuravlevmikhail.a65appshomeproject.domain.map.MapRepository
import io.reactivex.Single

class MapGateway(private val mapProvider: LocationProvider,
                 private val geoDecoder: GeoDecoder) : MapRepository {

    override fun getCurrentUserLocation(): Single<LatLng> {
        return mapProvider.getCurrentUserLocation()
    }

    override fun geoDecodeLocation(latLng: LatLng): Single<String> {
        return geoDecoder.geoDecodeLocation(latLng)
    }
}