package com.zhuravlevmikhail.a65appshomeproject.fragments.mapGeneral


import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.zhuravlevmikhail.a65appshomeproject.core.baseMap.BaseMapFragment
import com.zhuravlevmikhail.a65appshomeproject.core.baseMap.BaseMapPresenter

class GenMapFragment :
    BaseMapFragment() {
    override fun getOnMapClickListener() = GoogleMap.OnMapClickListener {  }

    override fun getBaseMapPresenter() = object : BaseMapPresenter {
            override fun onMapClicked(latLng: LatLng) {}
            override fun onMapCreated() {}
    }
}