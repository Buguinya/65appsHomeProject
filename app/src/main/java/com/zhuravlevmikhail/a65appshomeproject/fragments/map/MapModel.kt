package com.zhuravlevmikhail.a65appshomeproject.fragments.map

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION

object MapPermissions {

    val permissions= arrayOf(
        ACCESS_FINE_LOCATION,
        ACCESS_COARSE_LOCATION
    )
}
const val CAMERA_ZOOM = 15.0f