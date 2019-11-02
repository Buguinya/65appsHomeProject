package com.zhuravlevmikhail.a65appshomeproject.data.androidApi.map

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.LatLng
import io.reactivex.Single
import java.lang.Exception

class LocationProviderImpl(private val locationClient : FusedLocationProviderClient) :
    LocationProvider {
    override fun getCurrentUserLocation(): Single<LatLng> {
       return Single.create<LatLng> {emitter ->
            if (emitter.isDisposed) { return@create }
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