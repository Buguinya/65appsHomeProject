package com.zhuravlevmikhail.a65appshomeproject.data.network.mapRouter

import com.google.android.gms.maps.model.LatLng
import com.mapbox.geojson.Point
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute.Builder
import io.reactivex.Single

class MapRouterMapboxService(
    private val navigationRouteBuilder: Builder) : MapRouter{
    override fun getRoute(from: LatLng, to: LatLng): Single<List<LatLng>> {
        return Single.create {
            navigationRouteBuilder
                .origin(Point.fromLngLat(
                    from.longitude, from.latitude
                ))
                .destination(Point.fromLngLat(
                    to.longitude, to.latitude
                ))
                .build()
                .call
                .execute()
        }
    }
}