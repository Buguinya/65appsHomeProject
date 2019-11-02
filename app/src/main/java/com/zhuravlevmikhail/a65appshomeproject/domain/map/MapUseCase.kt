package com.zhuravlevmikhail.a65appshomeproject.domain.map

import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.innerFragments.ContactOnMapDomainEntity
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.innerFragments.LatLngDomainEntity
import io.reactivex.Completable
import io.reactivex.Single

class MapUseCase(private val mapRepository: MapRepository)
    : MapInteractor {

    override fun getCurrentUserLocation(): Single<LatLngDomainEntity> {
        return mapRepository.getCurrentUserLocation()
    }

    override fun geoDecodeLocation(latitude : Double, longitude : Double, key: String): Single<String> {
        val lngLatString = String.format("%s,%s", longitude, latitude)
        return mapRepository.geoDecodeLocation(lngLatString, key)
    }

    override fun saveContactAddress(latitude : Double, longitude : Double, address : String, contactId : Long) : Completable{
        val contactEntity =
            ContactOnMapDomainEntity(
                contactId,
                address,
                longitude.toString(),
                latitude.toString()
            )
        return mapRepository.saveContactLocation(contactEntity)
    }

    override fun getContactAddress(contactId: Long) : Single<ContactOnMapDomainEntity> {
        return mapRepository.getContactsLocation(contactId)
    }
}