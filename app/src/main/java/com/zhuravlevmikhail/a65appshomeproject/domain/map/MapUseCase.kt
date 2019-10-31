package com.zhuravlevmikhail.a65appshomeproject.domain.map

import com.google.android.gms.maps.model.LatLng
import io.reactivex.Single

class MapUseCase(private val mapRepository: MapRepository)
    : MapInteractor {

    override fun getCurrentUserLocation(): Single<LatLng> {
        return mapRepository.getCurrentUserLocation()
    }

    override fun geoDecodeLocation(latLng: LatLng, key: String): Single<String> {
        val lngLatString = String.format("%s,%s", latLng.longitude, latLng.latitude)
        return mapRepository.geoDecodeLocation(lngLatString, key)
    }
}