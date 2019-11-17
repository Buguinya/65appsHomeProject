package com.zhuravlevmikhail.a65appshomeproject.domain.map

import com.zhuravlevmikhail.a65appshomeproject.domain.entities.map.ContactOnMapEntity
import com.zhuravlevmikhail.a65appshomeproject.domain.entities.map.LatLngEntity
import io.reactivex.Completable
import io.reactivex.Single

interface MapRepository {
    fun getCurrentUserLocation() : Single<LatLngEntity>
    fun geoDecodeLocation(lngLat: String, key: String): Single<String>
    fun saveContactLocation(contactOnMapDomainEntity : ContactOnMapEntity): Completable
    fun getContactsLocation(id: Long): Single<ContactOnMapEntity>
    fun getAllLocations(): Single<List<ContactOnMapEntity>>
    fun getRoute(from: LatLngEntity, to: LatLngEntity, key: String): Single<List<LatLngEntity>>
}