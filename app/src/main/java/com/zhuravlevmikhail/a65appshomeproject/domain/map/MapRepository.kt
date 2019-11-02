package com.zhuravlevmikhail.a65appshomeproject.domain.map

import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.innerFragments.ContactOnMapDomainEntity
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.innerFragments.LatLngDomainEntity
import io.reactivex.Completable
import io.reactivex.Single

interface MapRepository {
    fun getCurrentUserLocation() : Single<LatLngDomainEntity>
    fun geoDecodeLocation(lngLat: String, key: String): Single<String>
    fun saveContactLocation(contactOnMapDomainEntity : ContactOnMapDomainEntity): Completable
    fun getContactsLocation(id: Long): Single<ContactOnMapDomainEntity>
    fun getAllLocations(): Single<List<ContactOnMapDomainEntity>>
}