package com.zhuravlevmikhail.a65appshomeproject.data.retrofit

import com.google.gson.annotations.SerializedName

data class YandexResponse (
    @SerializedName("response")
    val response : Response
)

data class Response (
    @SerializedName("GeoObjectCollection")
    val geoObjectCollection : GeoObjectCollection
)

data class GeoObjectCollection (
    @SerializedName("featureMember")
    val featureMember : List<FeatureMember>
)

data class FeatureMember (
    @SerializedName("GeoObject")
    val geoObject : GeoObject
)

data class GeoObject (
    @SerializedName("name")
    val address : String
)   