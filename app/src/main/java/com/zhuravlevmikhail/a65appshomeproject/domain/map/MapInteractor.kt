package com.zhuravlevmikhail.a65appshomeproject.domain.map

import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.innerFragments.ContactOnMapDomainEntity
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.innerFragments.LatLngDomainEntity
import io.reactivex.Completable
import io.reactivex.Single

interface MapInteractor {
    fun getCurrentUserLocation() : Single<LatLngDomainEntity>
    fun geoDecodeLocation(latitude : Double, longitude : Double,  key: String): Single<String>
    fun saveContactAddress(latitude : Double, longitude : Double, address: String, contactId: Long): Completable
    fun getContactAddress(contactId: Long): Single<ContactOnMapDomainEntity>
}