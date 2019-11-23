package com.zhuravlevmikhail.a65appshomeproject.data.network.mapRouter

import com.google.android.gms.maps.model.LatLng
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute
import io.reactivex.Single

class MapRouterMapboxService(
    private val navigationRoute: NavigationRoute) : MapRouter{
    override fun getRoute(from: LatLng, to: LatLng, key: String): Single<List<LatLng>> {
        return Single.create {

        }
    }
}