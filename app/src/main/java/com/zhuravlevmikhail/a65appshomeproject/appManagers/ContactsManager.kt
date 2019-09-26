package com.zhuravlevmikhail.a65appshomeproject.appManagers

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.zhuravlevmikhail.a65appshomeproject.common.AppConst


object ContactsManager {

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
}