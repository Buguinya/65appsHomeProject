package com.zhuravlevmikhail.a65appshomeproject.data.repositories

import com.zhuravlevmikhail.a65appshomeproject.data.androidApi.map.LocationProvider
import com.zhuravlevmikhail.a65appshomeproject.data.database.ContactsDBEntity
import com.zhuravlevmikhail.a65appshomeproject.data.database.ContactsStorage
import com.zhuravlevmikhail.a65appshomeproject.data.retrofit.GeoDecoder
import com.zhuravlevmikhail.a65appshomeproject.domain.map.MapRepository
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.innerFragments.ContactOnMapDomainEntity
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.innerFragments.LatLngDomainEntity
import io.reactivex.Completable
import io.reactivex.Single

class MapGateway(private val mapProvider : LocationProvider,
                 private val geoDecoder : GeoDecoder,
                 private val contactsStorage : ContactsStorage) : MapRepository {

    override fun getCurrentUserLocation(): Single<LatLngDomainEntity> {
        return mapProvider.getCurrentUserLocation()
            .map { latlng -> return@map LatLngDomainEntity(
                latlng.latitude,
                latlng.longitude
            )
            }
    }

    override fun geoDecodeLocation(lngLat: String, key: String): Single<String> {
        return geoDecoder.geoDecodeLocation(lngLat, key)
    }

    override fun saveContactLocation(contactOnMapDomainEntity: ContactOnMapDomainEntity) : Completable {
        with (contactOnMapDomainEntity) {
            val contactsDBEntity = ContactsDBEntity(
                id, address, longtude, latitude
            )
            return contactsStorage.addContact(contactsDBEntity)
        }
    }

    override fun getContactsLocation(id: Long) : Single<ContactOnMapDomainEntity> {
        return contactsStorage.getContactById(id)
    }

    override fun getAllLocations() : Single<List<ContactOnMapDomainEntity>> {
        return contactsStorage.getAllContacts()
            .map {locations ->
                locations.map {dbEntity ->
                    with (dbEntity) {
                        ContactOnMapDomainEntity(
                            id, address, longtude, latitude
                        )
                    }
                }
            }
    }
}