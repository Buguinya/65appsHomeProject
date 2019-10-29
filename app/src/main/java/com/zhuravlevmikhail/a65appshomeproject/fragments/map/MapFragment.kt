package com.zhuravlevmikhail.a65appshomeproject.fragments.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.zhuravlevmikhail.a65appshomeproject.R.layout.*
import moxy.MvpAppCompatFragment

class MapFragment : MvpAppCompatFragment(), MapView, OnMapReadyCallback {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(fragm_contact_location, container, false)
    }
    
    override fun onMapReady(p0: GoogleMap?) {

    }
}