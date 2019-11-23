package com.zhuravlevmikhail.a65appshomeproject.fragments.mapGeneral

import com.google.android.gms.maps.model.LatLng
import com.zhuravlevmikhail.a65appshomeproject.core.baseMap.BaseMapPresenter
import com.zhuravlevmikhail.a65appshomeproject.domain.entities.map.LatLngEntity
import com.zhuravlevmikhail.a65appshomeproject.domain.map.MapInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject


@InjectViewState
class GenMapPresenter
    @Inject constructor(
        private val mapInteractor: MapInteractor):
    MvpPresenter<GenMapView>(),
    BaseMapPresenter{

    private val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    override fun onMapCreated() {
        tryRequestPoints()
    }

    fun onTwoPointsSelected(origin : LatLng,
                            destination : LatLng) {
        getRoute(origin, destination)
    }

    private fun tryRequestPoints() {
        mapInteractor.getAllLocations()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { points ->
                    if (points.isNotEmpty()) {
                        points.forEach { point ->
                            viewState.addMarker(LatLng(
                                point.latitude.toDouble(),
                                point.longtude.toDouble()))
                        }
                        with(points[RANDOM_FOCUS_POINT_INDEX]) {
                            viewState.moveCameraToPosition(
                                LatLng(
                                    latitude.toDouble(),
                                    longtude.toDouble())
                            )
                        }
                    } else {
                        getCurrentUserLocation()
                    }
                }, {
                    throwable -> viewState.showError(throwable.localizedMessage)
                }
            ).addTo(compositeDisposable)
    }

    private fun getCurrentUserLocation() {
        mapInteractor.getCurrentUserLocation()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { latlng -> viewState.moveCameraToPosition(LatLng(latlng.latitude, latlng.longitude)) },
                { throwable -> viewState.showError(throwable.localizedMessage) }
            ).addTo(compositeDisposable)
    }

    private fun getRoute(origin : LatLng, destination : LatLng){
        val from = with(origin) {LatLngEntity(latitude, longitude)}
        val to = with(destination) {LatLngEntity(latitude, longitude)}
        mapInteractor.getRoute(from, to)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( { polyline ->
                val mappedPolyline = polyline.map {
                    with(it) {LatLng(latitude, longitude)}
                }
                viewState.showRoute(mappedPolyline)
            }, {
                viewState.showError(it.localizedMessage)
            }).addTo(compositeDisposable)
    }
}