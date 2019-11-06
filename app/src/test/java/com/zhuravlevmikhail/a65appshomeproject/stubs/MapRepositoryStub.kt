package com.zhuravlevmikhail.a65appshomeproject.stubs

import com.zhuravlevmikhail.a65appshomeproject.common.Utils
import com.zhuravlevmikhail.a65appshomeproject.domain.map.MapRepository
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.innerFragments.ContactOnMapDomainEntity
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.innerFragments.LatLngDomainEntity
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.internal.operators.completable.CompletableError
import java.lang.Exception
import java.util.*

const val USER_ADDRESS = "Address"
class MapRepositoryStub : MapRepository {

    override fun getCurrentUserLocation(): Single<LatLngDomainEntity> {
        return Single.fromCallable { LatLngDomainEntity(0.0, 0.0) }
    }

    override fun geoDecodeLocation(lngLat: String, key: String): Single<String> {
       return Single.fromCallable { if (Utils.isTrimmedNotEmpty(key)) USER_ADDRESS else throw Exception() }
    }

    override fun saveContactLocation(contactOnMapDomainEntity: ContactOnMapDomainEntity): Completable {
        return if (Utils.isTrimmedNotEmpty(contactOnMapDomainEntity.address)) Completable.complete()
        else CompletableError(Throwable())
    }

    override fun getContactsLocation(id: Long): Single<ContactOnMapDomainEntity> {
        return Single.fromCallable { if (id != 0.toLong()) ContactOnMapDomainEntity(
            id, USER_ADDRESS, "0", "0")
        else throw Exception()}
    }

    override fun getAllLocations(): Single<List<ContactOnMapDomainEntity>> {
        return Single.fromCallable { Collections.emptyList<ContactOnMapDomainEntity>() }
    }
}