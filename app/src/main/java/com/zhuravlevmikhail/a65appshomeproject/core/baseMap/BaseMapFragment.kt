package com.zhuravlevmikhail.a65appshomeproject.core.baseMap

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMapClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.zhuravlevmikhail.a65appshomeproject.R
import com.zhuravlevmikhail.a65appshomeproject.appManagers.PermissionManager
import com.zhuravlevmikhail.a65appshomeproject.common.AppConst.PERMISSION_REQUEST_CODE_LOCATION
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.innerFragments.*
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.innerFragments.PermissionsState.UNSUBMITTED
import kotlinx.android.synthetic.main.fragm_contact_location.*
import moxy.MvpAppCompatFragment

abstract class BaseMapFragment :
    MvpAppCompatFragment(),
    BaseMapView,
    OnMapReadyCallback{

    abstract fun getOnMapClickListener() : OnMapClickListener
    abstract fun getBaseMapPresenter() : BaseMapPresenter

    protected lateinit var googleMap: GoogleMap
    protected var locationPermissionState = UNSUBMITTED

    private val marker = MarkerOptions()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationPermissionState = savedInstanceState?.getInt(FRAGMENT_DATA_KEY_LOCATION_APPROVED)
            ?: UNSUBMITTED
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater
            .inflate(R.layout.fragm_contact_location,
                container,
                false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapViewContactLocation.onCreate(savedInstanceState)
        mapViewContactLocation.getMapAsync(this)
        getBaseMapPresenter().onMapCreated()
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

    override fun onDestroyView() {
        mapViewContactLocation.onDestroy()
        super.onDestroyView()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapViewContactLocation.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(FRAGMENT_DATA_KEY_LOCATION_APPROVED, locationPermissionState)
        mapViewContactLocation.onSaveInstanceState(outState)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE_LOCATION -> {
                locationPermissionState = if (grantResults[0] == PackageManager.PERMISSION_GRANTED) PermissionsState.APPROVED else PermissionsState.DENIED
                configMap(googleMap)
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun moveCameraToPosition(latLng: LatLng) {
        googleMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                latLng,
                CAMERA_ZOOM
            )
        )
    }

    override fun addMarker(latLng: LatLng) {
        googleMap.addMarker(marker.position(latLng))
    }

    override fun addMarker(latLng: LatLng, title: String)  {
        googleMap.addMarker(marker.position(latLng).title(title))
    }

    override fun showError(error: Int) {
        getToastShort(resources.getString(error))
    }

    override fun showError(error: String) {
        getToastShort(error)
    }

    override fun onMapReady(p0: GoogleMap?) {
        p0?.let {
            this.googleMap = it
            configMap(googleMap) }
    }

    protected fun enableLocationOptions(enable : Boolean) {
        googleMap.isMyLocationEnabled = enable
        googleMap.uiSettings.isMyLocationButtonEnabled = enable
    }

    private fun configMap(googleMap: GoogleMap) {
        if (locationPermissionState == UNSUBMITTED) {
            if (!PermissionManager.requestLocationPermission(this)) {
                locationPermissionState = PermissionsState.APPROVED
                configMap(googleMap)
            } else return
        }
        enableLocationOptions(locationPermissionState == PermissionsState.APPROVED)
        googleMap.setOnMapClickListener(getOnMapClickListener())
    }

    private fun getToastShort(message: String): Toast {
        return Toast.makeText(context, message, Toast.LENGTH_SHORT)
    }
}