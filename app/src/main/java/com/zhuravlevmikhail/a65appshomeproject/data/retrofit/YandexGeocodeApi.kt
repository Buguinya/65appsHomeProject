package com.zhuravlevmikhail.a65appshomeproject.data.retrofit

import com.google.android.gms.maps.model.LatLng
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YandexGeocodeApi {

    @GET("1.x/.")
    fun decodeLocation(
        @Query("geocode") latLng: LatLng,
        @Query("apikey") apikey: String,
        @Query("format") format: String
    ) : Call<Any>
}