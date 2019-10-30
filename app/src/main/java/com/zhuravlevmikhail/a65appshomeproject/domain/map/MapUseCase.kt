package com.zhuravlevmikhail.a65appshomeproject.domain.map

import com.google.android.gms.maps.model.LatLng
import io.reactivex.Single

class MapUseCase(private val mapRepository: MapRepository)
    : MapInteractor {

    override fun getCurrentUserLocation(): Single<LatLng> {
        return mapRepository.getCurrentUserLocation()
    }
}