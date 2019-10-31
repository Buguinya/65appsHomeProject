package com.zhuravlevmikhail.a65appshomeproject.data.repositories

import com.google.android.gms.maps.model.LatLng
import com.zhuravlevmikhail.a65appshomeproject.data.androidApi.map.LocationProvider
import com.zhuravlevmikhail.a65appshomeproject.data.database.ContactsDBEntity
import com.zhuravlevmikhail.a65appshomeproject.data.database.ContactsStorage
import com.zhuravlevmikhail.a65appshomeproject.data.retrofit.GeoDecoder
import com.zhuravlevmikhail.a65appshomeproject.domain.map.MapRepository
import io.reactivex.Completable
import io.reactivex.Single

class MapGateway(private val mapProvider : LocationProvider,
                 private val geoDecoder : GeoDecoder,
                 private val contactsStorage : ContactsStorage) : MapRepository {

    override fun getCurrentUserLocation(): Single<LatLng> {
        return mapProvider.getCurrentUserLocation()
    }

    override fun geoDecodeLocation(lngLat: String, key: String): Single<String> {
        return geoDecoder.geoDecodeLocation(lngLat, key)
    }

    override fun saveContactLocation(contactsDBEntity: ContactsDBEntity) : Completable {
        return contactsStorage.addContact(contactsDBEntity)
    }

    override fun getContactsLocation(id: Long) : Single<ContactsDBEntity> {
        return contactsStorage.getContactById(id)
    }

    override fun getAllLocation() : Single<List<ContactsDBEntity>> {
        return contactsStorage.getAllContacts()
    }
}