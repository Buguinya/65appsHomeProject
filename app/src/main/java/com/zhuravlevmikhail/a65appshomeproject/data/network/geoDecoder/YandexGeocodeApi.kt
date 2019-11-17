package com.zhuravlevmikhail.a65appshomeproject.data.network.geoDecoder

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface YandexGeocodeApi {

    @GET("1.x/.")
    fun decodeLocation(
        @Query("geocode") latLng: String,
        @Query("apikey") apikey: String,
        @Query("format") format: String
    ) : Single<YandexResponse>
}