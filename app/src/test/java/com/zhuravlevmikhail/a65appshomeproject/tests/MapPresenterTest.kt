package com.zhuravlevmikhail.a65appshomeproject.tests

import com.zhuravlevmikhail.a65appshomeproject.common.schedulersRX.TrampolineSchedulers
import com.zhuravlevmikhail.a65appshomeproject.domain.map.MapInteractor
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.innerFragments.MapPresenter
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.innerFragments.MapView
import com.zhuravlevmikhail.a65appshomeproject.mocks.LatLngMock
import com.zhuravlevmikhail.a65appshomeproject.stubs.MapInteractorStub
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MapPresenterTest {

    private lateinit var mapPresenter : MapPresenter
    private lateinit var mapInteractor: MapInteractor

    @Mock lateinit var mockView : MapView

    @Before
    fun testSet() {
        MockitoAnnotations.initMocks(this)
        mapInteractor = MapInteractorStub()
        mapPresenter = MapPresenter(
            mapInteractor, "key", 1, TrampolineSchedulers())
        mapPresenter.attachView(mockView)
    }

    @Test
    fun placeMarkerWhenReceivedLocation() {
        mapPresenter.onMapCreated()
        Mockito.verify(mockView).addMarker(LatLngMock.mock, "Address")
        Mockito.verify(mockView).moveCameraToPosition(LatLngMock.mock)
    }
}