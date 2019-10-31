package com.zhuravlevmikhail.a65appshomeproject.data.retrofit

import com.zhuravlevmikhail.a65appshomeproject.common.AppConst.FORMAT
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response

const val INDEX_ADDRESS = 0
class GeoDecoderYandex (private val yandexGeocodeApi: YandexGeocodeApi) : GeoDecoder {

    override fun geoDecodeLocation(lngLat: String, key: String): Single<String> {
       return Single.create {emmiter ->
           try {
               val response : YandexResponse? = yandexGeocodeApi
                   .decodeLocation(lngLat, key, FORMAT)
                   .execute()
                   .body()
               if (response != null) {
                   val address  = response
                       .response
                       .geoObjectCollection
                       .featureMember[INDEX_ADDRESS]
                       .geoObject
                       .address
                   emmiter.onSuccess(address)
               } else { emmiter.onError(Throwable("Empty response")) }
           } catch (t : Throwable) {
                emmiter.onError(t)
           }
       }
    }
}