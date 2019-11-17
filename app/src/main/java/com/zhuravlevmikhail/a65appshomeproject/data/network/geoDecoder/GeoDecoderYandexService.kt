package com.zhuravlevmikhail.a65appshomeproject.data.network.geoDecoder

import com.zhuravlevmikhail.a65appshomeproject.common.AppConst.FORMAT
import io.reactivex.Single

const val INDEX_ADDRESS = 0
class GeoDecoderYandex (private val yandexGeocodeApi: YandexGeocodeApi) :
    GeoDecoder {

    override fun geoDecodeLocation(lngLat: String, key: String): Single<String> {
        return yandexGeocodeApi.decodeLocation(lngLat, key, FORMAT)
            .map { yandexResponse ->
                return@map yandexResponse
                        .response
                        .geoObjectCollection
                        .featureMember[INDEX_ADDRESS]
                        .geoObject
                        .address
            }
    }
}