package com.zhuravlevmikhail.a65appshomeproject.data.retrofit

import com.google.android.gms.maps.model.LatLng
import com.zhuravlevmikhail.a65appshomeproject.common.AppConst.FORMAT
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response

class GeoDecoderYandex (private val yandexGeocodeApi: YandexGeocodeApi) : GeoDecoder {

    override fun geoDecodeLocation(latLng: LatLng, key: String): Single<String> {
        val mappedCoord = String.format("%s,%s", latLng.longitude, latLng.latitude)
        yandexGeocodeApi.decodeLocation(mappedCoord, key, FORMAT).enqueue(object : retrofit2.Callback<YandexResponse> {
            override fun onFailure(call: Call<YandexResponse>, t: Throwable) {
                t
            }
            override fun onResponse(call: Call<YandexResponse>, response: Response<YandexResponse>) {
                if (response.isSuccessful) {
                    val address =
                        response.body()!!.response.geoObjectCollection.featureMember[0].geoObject.address
                    address.length
                }
            }
        })
       return Single.create {
       }
    }
}