package com.zhuravlevmikhail.a65appshomeproject.domain.map

import com.google.android.gms.maps.model.LatLng
import com.zhuravlevmikhail.a65appshomeproject.data.database.ContactsDBEntity
import io.reactivex.Completable
import io.reactivex.Single

class MapUseCase(private val mapRepository: MapRepository)
    : MapInteractor {

    override fun getCurrentUserLocation(): Single<LatLng> {
        return mapRepository.getCurrentUserLocation()
    }

    override fun geoDecodeLocation(latLng: LatLng, key: String): Single<String> {
        val lngLatString = String.format("%s,%s", latLng.longitude, latLng.latitude)
        return mapRepository.geoDecodeLocation(lngLatString, key)
    }

    override fun saveContactAddress(latLng: LatLng, address : String, contactId : Long) : Completable{
        val contactEntity = ContactsDBEntity(
            contactId,
            address,
            latLng.longitude.toString(),
            latLng.latitude.toString()
        )
        return mapRepository.saveContactLocation(contactEntity)
    }

    override fun getContactAddress(contactId: Long) : Single<ContactsDBEntity> {
        return mapRepository.getContactsLocation(contactId)
    }
}