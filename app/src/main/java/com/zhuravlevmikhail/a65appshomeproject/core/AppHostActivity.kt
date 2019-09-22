package com.zhuravlevmikhail.a65appshomeproject.core

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zhuravlevmikhail.a65appshomeproject.R
import com.zhuravlevmikhail.a65appshomeproject.appManagers.ContactsManager
import com.zhuravlevmikhail.a65appshomeproject.appManagers.PageManager
import com.zhuravlevmikhail.a65appshomeproject.common.AppConst.PERMISSION_REQUEST_CODE_CONTACTS

class AppHostActivity : AppCompatActivity(R.layout.activity_host) {

    private lateinit var pageManager: PageManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageManager = App.instance.pageManager
        if (savedInstanceState == null) {
            pageManager.onCreate(this)
            pageManager.setContactsPage()
        }
    }

    override fun onResume() {
        pageManager.onResume(this)
        super.onResume()
    }

    override fun onPause() {
        pageManager.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        pageManager.onDestroy()
        super.onDestroy()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pageManager.onContactsAccessGranted()
            }
        }
    }
}
