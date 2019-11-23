package com.zhuravlevmikhail.a65appshomeproject.data.network.mapRouter

import com.google.android.gms.maps.model.LatLng
import com.zhuravlevmikhail.a65appshomeproject.common.AppConst.FORMAT
import io.reactivex.Single

/** Build route from google maps api.
 * @deprecated This class require paid api key
 */
class MapRouterGoogleService(
    private val mapRouterApi: MapRouterGoogleApi,
    private val apiKey : String
) : MapRouter {
    override fun getRoute(from: LatLng, to: LatLng)
            : Single<List<LatLng>> {
        return  mapRouterApi.getRoute(
            FORMAT,
            from,
            to,
            apiKey,
            "walking")
    }
}