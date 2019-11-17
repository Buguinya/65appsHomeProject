package com.zhuravlevmikhail.a65appshomeproject.domain.map

import com.zhuravlevmikhail.a65appshomeproject.domain.entities.map.ContactOnMapEntity
import com.zhuravlevmikhail.a65appshomeproject.domain.entities.map.LatLngEntity
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

class MapUseCase(private val mapRepository: MapRepository)
    : MapInteractor {

    override fun getCurrentUserLocation(): Single<LatLngEntity> {
        return mapRepository.getCurrentUserLocation()
    }

    override fun geoDecodeLocation(latitude : Double, longitude : Double, key: String): Single<String> {
        val lngLatString = String.format(Locale.getDefault(),"%s,%s", longitude, latitude)
        return mapRepository.geoDecodeLocation(lngLatString, key)
    }

    override fun saveContactAddress(latitude : Double, longitude : Double, address : String, contactId : Long) : Completable{
        val contactEntity =
            ContactOnMapEntity(
                contactId,
                address,
                longitude.toString(),
                latitude.toString()
            )
        return mapRepository.saveContactLocation(contactEntity)
    }

    override fun getContactAddress(contactId: Long) : Single<ContactOnMapEntity> {
        return mapRepository.getContactsLocation(contactId)
    }

    override fun getAllLocations(): Single<List<ContactOnMapEntity>> {
        return mapRepository.getAllLocations()
    }

    override fun getRoute(
        from: LatLngEntity,
        to: LatLngEntity,
        key: String
    ): Single<List<LatLngEntity>> {
        return mapRepository.getRoute(
            from, to, key
        )
    }
}