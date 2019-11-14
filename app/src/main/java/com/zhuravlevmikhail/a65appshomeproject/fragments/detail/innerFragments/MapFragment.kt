package com.zhuravlevmikhail.a65appshomeproject.fragments.detail.innerFragments

import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.zhuravlevmikhail.a65appshomeproject.core.App
import com.zhuravlevmikhail.a65appshomeproject.core.baseMap.BaseMapFragment
import com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.presentation.ContactInfoModule
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.FRAGMENT_DATA_KEY_CONTACT_ID
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import javax.inject.Provider

class MapFragment :
    BaseMapFragment(),
    MapView {
    
    override fun getOnMapClickListener() = GoogleMap.OnMapClickListener {
         place : LatLng -> mapPresenter.onMapClicked(place)}

    override fun getBaseMapPresenter() = mapPresenter

    private val marker = MarkerOptions()

    @Inject lateinit var presenterProvider: Provider<MapPresenter>

    @InjectPresenter lateinit var mapPresenter: MapPresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val contactId = arguments?.getLong(FRAGMENT_DATA_KEY_CONTACT_ID) ?: 0
        App.instance.appComponent
            .apply {
                plusMapComponent(ContactInfoModule(contactId))
                    .inject(this@MapFragment)
            }
    }

    override fun addMarker(latLng: LatLng) {
        googleMap.addMarker(marker.position(latLng))
    }

    override fun addMarker(latLng: LatLng, title: String)  {
        googleMap.addMarker(marker.position(latLng).title(title))
    }

    @ProvidePresenter
    fun providePresenter() : MapPresenter {
        return presenterProvider.get()
    }
}