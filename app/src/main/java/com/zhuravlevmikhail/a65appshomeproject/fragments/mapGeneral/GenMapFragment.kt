package com.zhuravlevmikhail.a65appshomeproject.fragments.mapGeneral

import android.content.Context
import android.graphics.Color
import androidx.lifecycle.Lifecycle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import com.zhuravlevmikhail.a65appshomeproject.core.App
import com.zhuravlevmikhail.a65appshomeproject.core.baseMap.BaseMapFragment
import com.zhuravlevmikhail.a65appshomeproject.core.baseMap.BaseMapPresenter
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import javax.inject.Provider

class GenMapFragment :
    BaseMapFragment(),
    GenMapView,
    RouteRequireListener{

    private lateinit var routableMap : RoutableMap
    private lateinit var route : Polyline

    override fun getOnMapClickListener() = GoogleMap.OnMapClickListener {
    }

    override fun getBaseMapPresenter() = genMapPresenter

    @Inject
    lateinit var presenterProvider: Provider<GenMapPresenter>

    @InjectPresenter
    lateinit var genMapPresenter: GenMapPresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App
            .instance
            .appComponent
            .plucGenMapComponent()
            .inject(this@GenMapFragment)
    }

    override fun onTwoPointsSelected(first: LatLng, second: LatLng) {
        genMapPresenter.onTwoPointsSelected(first, second)
    }

    override fun onMapReady(p0: GoogleMap?) {
        super.onMapReady(p0)
        routableMap = RoutableMap(googleMap, this)
        routableMap.onMapReady()
    }

    override fun showRoute(polyline: List<LatLng>) {
        val polylineOptions = PolylineOptions()
        polylineOptions.addAll(polyline)
        polylineOptions.color(Color.RED)
        route = googleMap.addPolyline(polylineOptions)
        val builder = LatLngBounds.Builder()
        for (point in polyline) {
            with(point) {
                builder.include(LatLng(
                    latitude,longitude)
                )
            }
        }
        val bounds = builder.build()
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100))
    }

    @ProvidePresenter
    fun providePresenter() : GenMapPresenter {
        return presenterProvider.get()
    }
}