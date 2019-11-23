package com.zhuravlevmikhail.a65appshomeproject.domain.map

import com.zhuravlevmikhail.a65appshomeproject.domain.entities.map.ContactOnMapEntity
import com.zhuravlevmikhail.a65appshomeproject.domain.entities.map.LatLngEntity
import io.reactivex.Completable
import io.reactivex.Single

interface MapInteractor {
    fun getCurrentUserLocation() : Single<LatLngEntity>
    fun geoDecodeLocation(latitude : Double, longitude : Double,  key: String): Single<String>
    fun saveContactAddress(latitude : Double, longitude : Double, address: String, contactId: Long): Completable
    fun getContactAddress(contactId: Long): Single<ContactOnMapEntity>
    fun getAllLocations(): Single<List<ContactOnMapEntity>>
    fun getRoute(from: LatLngEntity, to: LatLngEntity): Single<List<LatLngEntity>>
}