package com.zhuravlevmikhail.a65appshomeproject.domain.map

import com.google.android.gms.maps.model.LatLng
import com.zhuravlevmikhail.a65appshomeproject.data.database.ContactsDBEntity
import io.reactivex.Completable
import io.reactivex.Single

interface MapInteractor {

    fun getCurrentUserLocation() : Single<LatLng>
    fun geoDecodeLocation(latLng: LatLng, key: String): Single<String>
    fun saveContactAddress(latLng: LatLng, address: String, contactId: Long): Completable
    fun getContactAddress(contactId: Long): Single<ContactsDBEntity>
}