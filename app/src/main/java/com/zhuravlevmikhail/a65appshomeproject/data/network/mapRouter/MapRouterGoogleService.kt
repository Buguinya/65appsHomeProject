package com.zhuravlevmikhail.a65appshomeproject.data.network.mapRouter

import com.google.android.gms.maps.model.LatLng
import com.zhuravlevmikhail.a65appshomeproject.common.AppConst
import com.zhuravlevmikhail.a65appshomeproject.common.AppConst.FORMAT
import io.reactivex.Single

class MapRouterGoogleService(
    private val mapRouterApi: MapRouterApi
) : MapRouter {

    override fun getRoute(from: LatLng, to: LatLng, key : String)
            : Single<List<LatLng>> {
        return mapRouterApi.getRoute(
            FORMAT,
            from,
            to,
            key)
    }
}