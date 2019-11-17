package com.zhuravlevmikhail.a65appshomeproject.fragments.mapGeneral

import android.content.Context
import androidx.lifecycle.Lifecycle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
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

    private val routableMap = RoutableMap(googleMap, this)

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
        routableMap.onMapReady()
    }

    @ProvidePresenter
    fun providePresenter() : GenMapPresenter {
        return presenterProvider.get()
    }
}