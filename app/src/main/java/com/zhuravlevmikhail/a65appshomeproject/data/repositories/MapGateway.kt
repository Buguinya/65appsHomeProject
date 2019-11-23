package com.zhuravlevmikhail.a65appshomeproject.data.repositories

import com.google.android.gms.maps.model.LatLng
import com.zhuravlevmikhail.a65appshomeproject.data.androidApi.map.LocationProvider
import com.zhuravlevmikhail.a65appshomeproject.data.database.ContactsDBEntity
import com.zhuravlevmikhail.a65appshomeproject.data.database.ContactsStorage
import com.zhuravlevmikhail.a65appshomeproject.data.network.geoDecoder.GeoDecoder
import com.zhuravlevmikhail.a65appshomeproject.data.network.mapRouter.MapRouter
import com.zhuravlevmikhail.a65appshomeproject.domain.entities.map.ContactOnMapEntity
import com.zhuravlevmikhail.a65appshomeproject.domain.entities.map.LatLngEntity
import com.zhuravlevmikhail.a65appshomeproject.domain.map.MapRepository
import io.reactivex.Completable
import io.reactivex.Single

class MapGateway(private val mapProvider : LocationProvider,
                 private val geoDecoder : GeoDecoder,
                 private val contactsStorage : ContactsStorage,
                 private val mapRouter: MapRouter) : MapRepository {

    override fun getCurrentUserLocation(): Single<LatLngEntity> {
        return mapProvider.getCurrentUserLocation()
            .map { latlng -> return@map LatLngEntity(
                latlng.latitude,
                latlng.longitude)
            }
    }

    override fun geoDecodeLocation(lngLat: String, key: String): Single<String> {
        return geoDecoder.geoDecodeLocation(lngLat, key)
    }

    override fun saveContactLocation(contactOnMapDomainEntity: ContactOnMapEntity) : Completable {
        with (contactOnMapDomainEntity) {
            val contactsDBEntity = ContactsDBEntity(
                id, address, longtude, latitude
            )
            return contactsStorage.addContact(contactsDBEntity)
        }
    }

    override fun getContactsLocation(id: Long) : Single<ContactOnMapEntity> {
        return contactsStorage.getContactById(id)
    }

    override fun getAllLocations() : Single<List<ContactOnMapEntity>> {
        return contactsStorage.getAllContacts()
            .map {locations ->
                locations.map {dbEntity ->
                    with (dbEntity) {
                        ContactOnMapEntity(
                            id, address, longtude, latitude
                        )
                    }
                }
            }
    }

    override fun getRoute(from : LatLngEntity, to : LatLngEntity) :
            Single<List<LatLngEntity>> {
        val origin = with(from) {LatLng(latitude, longitude)}
        val destinaion = with(to) {LatLng(latitude, longitude)}
        return mapRouter.
            getRoute(origin, destinaion)
            .map {points ->
                points.map { point ->
                    with(point) {LatLngEntity(latitude, longitude)}
                }
            }
    }
}