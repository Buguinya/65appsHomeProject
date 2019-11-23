package com.zhuravlevmikhail.a65appshomeproject.data.network.mapRouter

import com.google.android.gms.maps.model.LatLng
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MapRouterGoogleApi {

    @GET("maps/api/directions/{format}")
    fun getRoute(
        @Path("format") format : String,
        @Query("origin") origin : LatLng,
        @Query("destination") destination : LatLng,
        @Query("key") key : String,
        @Query("mode") mode : String) : Single<List<LatLng>>
}