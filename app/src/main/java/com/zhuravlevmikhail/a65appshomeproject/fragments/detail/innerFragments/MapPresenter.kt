package com.zhuravlevmikhail.a65appshomeproject.fragments.detail.innerFragments

import android.util.Log
import com.google.android.gms.maps.model.LatLng
import com.zhuravlevmikhail.a65appshomeproject.common.schedulersRX.SchedulersProvider
import com.zhuravlevmikhail.a65appshomeproject.domain.map.MapInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class MapPresenter
    @Inject constructor(private val mapInteractor: MapInteractor,
                        private val apiKey: String,
                        private val contactId: Long,
                        private val schedulersProvider: SchedulersProvider) : MvpPresenter<MapView>() {

    private val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    fun onMapClicked(latLng: LatLng) {
        geoDecodeLocation(latLng)
    }

    fun noLocation() {
        getCurrentUserLocation()
    }

    fun onMapCreated() {
        getContactsLocation(contactId)
    }

    private fun geoDecodeLocation(latLng: LatLng) {
        mapInteractor.geoDecodeLocation(latLng.latitude, latLng.longitude, apiKey)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
            .subscribe (
                { address ->
                    viewState.addMarker(latLng, address)
                    if (contactId != UNKNOWN_CONTACT) {
                        saveContactLocation(contactId, latLng, address)
                    }},
                { throwable -> viewState.showError(throwable.localizedMessage)}
            ).addTo(compositeDisposable)
    }

    private fun getCurrentUserLocation() {
        mapInteractor.getCurrentUserLocation()
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
            .subscribe(
                { latlng -> viewState.moveCameraToPosition(LatLng(latlng.latitude, latlng.longitude)) },
                { throwable -> viewState.showError(throwable.localizedMessage) }
            ).addTo(compositeDisposable)
    }

    private fun getContactsLocation(contactId : Long) {
        mapInteractor.getContactAddress(contactId)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
            .subscribe(
                { contact ->
                    val latLng = LatLng(contact.latitude.toDouble(), contact.longtude.toDouble())
                    viewState.addMarker(latLng, contact.address)
                    viewState.moveCameraToPosition(latLng)},
                { getCurrentUserLocation() }
            ).addTo(compositeDisposable)
    }

    private fun saveContactLocation(contactId: Long, latLng: LatLng, address : String) {
        mapInteractor.saveContactAddress(latLng.latitude, latLng.longitude,address, contactId)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
            .subscribe(
                { Log.d(this::class.simpleName, "saveContactLocation: Contact uploaded to database")},
                { Log.d(this::class.simpleName, "saveContactLocation: Problem occurred")}
            )
            .addTo(compositeDisposable)
    }
}