package com.zhuravlevmikhail.a65appshomeproject.appManagers

import android.Manifest
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.zhuravlevmikhail.a65appshomeproject.common.AppConst
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.innerFragments.MapPermissions.permissions


object PermissionManager {

    fun requestContactsPermission(fragment: Fragment): Boolean {
        fragment.context?.let { context ->
            val isPermissionDenied = (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_DENIED)
            if (isPermissionDenied) {
                fragment.requestPermissions(
                    arrayOf(Manifest.permission.READ_CONTACTS),
                    AppConst.PERMISSION_REQUEST_CODE_CONTACTS
                )
            }
            return isPermissionDenied
        }
        return false
    }

    fun requestLocationPermission(fragment: Fragment): Boolean  {
        fragment.context?.let { context ->
            val isPermissionDenied =
                    (ContextCompat.checkSelfPermission(
                        context,
                        ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_DENIED) &&
                    (ContextCompat.checkSelfPermission(
                        context,
                        ACCESS_COARSE_LOCATION
                        ) == PackageManager.PERMISSION_DENIED)
            if (isPermissionDenied) {
                fragment.requestPermissions(
                    permissions,
                    AppConst.PERMISSION_REQUEST_CODE_LOCATION
                )
            }
            return isPermissionDenied
        }
        return false
    }
}