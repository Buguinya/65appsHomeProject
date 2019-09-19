package com.zhuravlevmikhail.a65appshomeproject.appManagers

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.zhuravlevmikhail.a65appshomeproject.common.AppConst

interface ContactsManagerInterface {

}

class ContactsManager : ContactsManagerInterface {

    companion object {
        private lateinit var _instance : ContactsManager
        fun getInstance() : ContactsManager{
            if (!::_instance.isInitialized) {
                _instance = ContactsManager()
            }
            return _instance
        }
    }

    fun requestCameraPermission(activity : Activity): Boolean {
        val isPermissionDenied = (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED)
        if (isPermissionDenied){
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.READ_CONTACTS),
                AppConst.PERMISSION_REQUEST_CODE_CONTACTS
            )
        }
        return isPermissionDenied
    }
}