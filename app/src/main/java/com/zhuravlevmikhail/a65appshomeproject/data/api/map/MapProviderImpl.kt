package com.zhuravlevmikhail.a65appshomeproject.data.api.map

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.LatLng
import com.zhuravlevmikhail.a65appshomeproject.data.api.map.MapProvider
import io.reactivex.Single
import java.lang.Exception

class MapProviderImpl(private val locationClient : FusedLocationProviderClient) :
    MapProvider {
    override fun getCurrentUserLocation(): Single<LatLng> {
       return Single.create<LatLng> {emitter ->
            try {
                locationClient.lastLocation.addOnSuccessListener {location ->
                    if (location != null) emitter.onSuccess(
                        LatLng(
                            location.latitude,
                            location.longitude
                    )) else {
                        emitter.onError(Throwable("Null location"))
                    }
                }
            } catch (e : Exception) {
                emitter.onError(e)
            }
        }
    }
}