package com.zhuravlevmikhail.a65appshomeproject

import com.zhuravlevmikhail.a65appshomeproject.data.database.ContactsDBEntity
import com.zhuravlevmikhail.a65appshomeproject.domain.map.MapRepository
import com.zhuravlevmikhail.a65appshomeproject.domain.map.MapUseCase
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.innerFragments.ContactOnMapDomainEntity
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.innerFragments.LatLngDomainEntity
import com.zhuravlevmikhail.a65appshomeproject.stubs.MapRepositoryStub
import com.zhuravlevmikhail.a65appshomeproject.stubs.USER_ADDRESS
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import java.lang.Exception

class MapUseCaseTest {

    private lateinit var mapRepository: MapRepository
    private lateinit var mapUseCase : MapUseCase

    @Before
    fun testSet() {
        mapRepository = MapRepositoryStub()
        mapUseCase = MapUseCase(mapRepository)
    }

    @Test
    fun returnLocationIfHasLocation() {
        val testObserver = TestObserver.create<LatLngDomainEntity>()
        mapUseCase.getCurrentUserLocation().subscribe(testObserver)
        testObserver
            .assertSubscribed()
            .assertNoErrors()
            .assertComplete()
            .dispose()
    }

    @Test
    fun returnAddressWhenDecodeSucceed() {
        val testObserver = TestObserver.create<String>()
        mapUseCase.geoDecodeLocation(0.0, 0.0, "Key")
            .subscribe(testObserver)
        testObserver
            .assertSubscribed()
            .assertNoErrors()
            .assertComplete()
            .dispose()
    }

    @Test
    fun returnErrorWhenDecodeFailed() {
        val testObserver = TestObserver.create<String>()
        mapUseCase.geoDecodeLocation(0.0, 0.0, "")
            .subscribe(testObserver)
        testObserver
            .assertSubscribed()
            .assertNoValues()
            .assertError(Exception::class.java)
            .dispose()
    }

    @Test
    fun saveContactWhenContactValid() {
        val testObserver = TestObserver.create<Any>()
        mapUseCase
            .saveContactAddress(0.0, 0.0, USER_ADDRESS, 0)
            .subscribe(testObserver)
        testObserver
            .assertSubscribed()
            .assertNoErrors()
            .assertComplete()
            .dispose()
    }

    @Test
    fun getErrorWhenSavingContactFailed() {
        val testObserver = TestObserver.create<Any>()
        mapUseCase
            .saveContactAddress(0.0, 0.0, "", 0)
            .subscribe(testObserver)
        testObserver
            .assertSubscribed()
            .assertNoValues()
            .assertNotComplete()
            .dispose()
    }

    @Test
    fun returnAddressWhenFoundAddress() {
        val testObserver = TestObserver.create<ContactOnMapDomainEntity>()
        mapUseCase
            .getContactAddress(1)
            .subscribe(testObserver)
        testObserver
            .assertSubscribed()
            .assertNoErrors()
            .assertComplete()
            .dispose()
    }

    @Test
    fun getErrorWhenObservingAddressFailed() {
        val testObserver = TestObserver.create<ContactOnMapDomainEntity>()
        mapUseCase
            .getContactAddress(0)
            .subscribe(testObserver)
        testObserver
            .assertSubscribed()
            .assertNoValues()
            .assertNotComplete()
            .dispose()
    }
}