package com.zhuravlevmikhail.a65appshomeproject.data.retrofit

import com.google.android.gms.maps.model.LatLng
import com.zhuravlevmikhail.a65appshomeproject.common.AppConst.FORMAT
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response

class GeoDecoderYandex (private val yandexGeocodeApi: YandexGeocodeApi) : GeoDecoder {

    override fun geoDecodeLocation(latLng: LatLng, key: String): Single<String> {
        yandexGeocodeApi.decodeLocation(latLng, key, FORMAT).enqueue(object : retrofit2.Callback<Any> {
            override fun onFailure(call: Call<Any>, t: Throwable) {
                t
            }

            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                response
            }

        })
       return Single.create {

       }
    }
}