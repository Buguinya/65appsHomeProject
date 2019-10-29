package com.zhuravlevmikhail.a65appshomeproject.fragments.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.zhuravlevmikhail.a65appshomeproject.R.layout.*
import kotlinx.android.synthetic.main.fragm_contact_location.*
import moxy.MvpAppCompatFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.zhuravlevmikhail.a65appshomeproject.appManagers.PermissionManager
import com.zhuravlevmikhail.a65appshomeproject.common.AppConst
import com.zhuravlevmikhail.a65appshomeproject.common.AppConst.PERMISSION_REQUEST_CODE_LOCATION
import com.zhuravlevmikhail.a65appshomeproject.fragments.map.MapPermissions.permissions

class MapFragment : MvpAppCompatFragment(), MapView {

    private lateinit var googleMap: GoogleMap

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
        mapViewContactLocation.onDestroy()
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
                    configMap(googleMap)
                }
                else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }
    }

    private fun configMap(googleMap: GoogleMap) {
        if (PermissionManager.requestLocationPermission(this)) {return}
        googleMap.setMinZoomPreference(12.toFloat())
        val ny = LatLng(40.7143528, -74.0059731)
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(ny))
    }
}