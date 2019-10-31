package com.zhuravlevmikhail.a65appshomeproject.fragments.map

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.maps.GoogleMap
import com.zhuravlevmikhail.a65appshomeproject.R.layout.*
import kotlinx.android.synthetic.main.fragm_contact_location.*
import moxy.MvpAppCompatFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.zhuravlevmikhail.a65appshomeproject.appManagers.PermissionManager
import com.zhuravlevmikhail.a65appshomeproject.common.AppConst.PERMISSION_REQUEST_CODE_LOCATION
import com.zhuravlevmikhail.a65appshomeproject.core.App
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.FRAGMENT_DATA_KEY_CONTACT_ID
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import javax.inject.Provider

class MapFragment : MvpAppCompatFragment(), MapView {

    private lateinit var googleMap: GoogleMap
    private val marker = MarkerOptions()
    private var contactId : Long = UNKNOWN_CONtACT
    private var isLocationDenied = true

    @Inject
    lateinit var presenterProvider: Provider<MapPresenter>

    @InjectPresenter
    lateinit var mapPresenter: MapPresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.instance.appComponent.plusMapComponent().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contactId = arguments?.getLong(FRAGMENT_DATA_KEY_CONTACT_ID) ?: UNKNOWN_CONtACT
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(fragm_contact_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapViewContactLocation.onCreate(savedInstanceState)
        mapViewContactLocation.getMapAsync { googleMap ->
            googleMap?.let {
                this.googleMap = it
                configMap(googleMap) }
        }
        if (contactId != UNKNOWN_CONtACT) {
            mapPresenter.onContactIdInitialized(contactId)
        }
    }
    
    override fun onStart() {
        super.onStart()
        mapViewContactLocation.onStart()
    }

    override fun onResume() {
        mapViewContactLocation.onResume()
        super.onResume()
    }

    override fun onPause() {
        mapViewContactLocation.onPause()
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapViewContactLocation.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
      //  mapViewContactLocation.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapViewContactLocation.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapViewContactLocation.onSaveInstanceState(outState)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
            when (requestCode) {
                PERMISSION_REQUEST_CODE_LOCATION -> {
                    isLocationDenied
                    configMap(googleMap)
                }
                else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }
    }

    override fun addMarker(latLng: LatLng) {
        googleMap.addMarker(marker.position(latLng))
    }

    override fun addMarker(latLng: LatLng, title: String)  {
        googleMap.addMarker(marker.position(latLng).title(title))
    }

    override fun moveCameraToPosition(latLng: LatLng) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, CAMERA_ZOOM))
    }

    override fun showError(error: Int) {
        getToastShort(resources.getString(error))
    }

    override fun showError(error: String) {
        getToastShort(error)
    }

    @ProvidePresenter
    fun providePresenter() : MapPresenter {
        return presenterProvider.get()
    }

    private fun configMap(googleMap: GoogleMap) {
        isLocationDenied =  PermissionManager.requestLocationPermission(this)
        enableLocationOptions(!isLocationDenied)
        googleMap.setOnMapClickListener {
            mapPresenter.onMapClicked(it)
        }
    }

    private fun getToastShort(message: String): Toast {
        return Toast.makeText(context, message, Toast.LENGTH_SHORT)
    }
    
    private fun enableLocationOptions(enable : Boolean) {
        googleMap.isMyLocationEnabled = enable
        googleMap.uiSettings.isMyLocationButtonEnabled = enable
    }
}