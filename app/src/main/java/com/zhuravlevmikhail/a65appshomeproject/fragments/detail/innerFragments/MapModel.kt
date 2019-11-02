package com.zhuravlevmikhail.a65appshomeproject.fragments.detail.innerFragments

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.room.PrimaryKey

object MapPermissions {

    val permissions= arrayOf(
        ACCESS_FINE_LOCATION,
        ACCESS_COARSE_LOCATION
    )
}
const val CAMERA_ZOOM = 15.0f

const val FRAGMENT_DATA_KEY_LOCATION_APPROVED = "FRAGMENT_DATA_KEY_LOCATION_APPROVED"

const val UNKNOWN_CONTACT : Long = 0

data class ContactOnMapDomainEntity (
    val id : Long,
    val address : String,
    val longtude : String,
    val latitude : String
)

data class LatLngDomainEntity (
    val latitude: Double,
    val longitude: Double
)

object PermissionsState {
    const val UNSUBMITTED = 0
    const val APPROVED = 1
    const val DENIED = 2
}