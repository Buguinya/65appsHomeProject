package com.zhuravlevmikhail.a65appshomeproject.core

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.zhuravlevmikhail.a65appshomeproject.R
import com.zhuravlevmikhail.a65appshomeproject.appManagers.ContactsManager
import com.zhuravlevmikhail.a65appshomeproject.appManagers.PageManager
import com.zhuravlevmikhail.a65appshomeproject.common.AppConst.PERMISSION_REQUEST_CODE_CONTACTS
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command

class AppHostActivity : AppCompatActivity(R.layout.activity_host) {

    private lateinit var pageManager: PageManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageManager = App.instance.pageManager
        if (savedInstanceState == null) {
            pageManager.onCreate(this)
            App.instance.cicerone.router.newRootScreen(ContactsScreen())
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.cicerone.navigatorHolder.setNavigator(navigator)
    }

    override fun onResume() {
        pageManager.onResume(this)
        super.onResume()
    }

    override fun onPause() {
        pageManager.onPause()
        super.onPause()
        App.instance.cicerone.navigatorHolder.removeNavigator()
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

    private val navigator = SupportAppNavigator(this, R.id.fragmentsContainer)
}
