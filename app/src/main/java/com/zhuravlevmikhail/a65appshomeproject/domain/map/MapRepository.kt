package com.zhuravlevmikhail.a65appshomeproject.domain.map

import com.google.android.gms.maps.model.LatLng
import com.zhuravlevmikhail.a65appshomeproject.data.database.ContactsDBEntity
import io.reactivex.Completable
import io.reactivex.Single

interface MapRepository {

    fun getCurrentUserLocation() : Single<LatLng>
    fun geoDecodeLocation(lngLat: String, key: String): Single<String>
    fun saveContactLocation(contactsDBEntity: ContactsDBEntity): Completable
    fun getContactsLocation(id: Long): Single<ContactsDBEntity>
    fun getAllLocation(): Single<List<ContactsDBEntity>>
}