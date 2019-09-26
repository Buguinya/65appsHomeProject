package com.zhuravlevmikhail.a65appshomeproject.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zhuravlevmikhail.a65appshomeproject.R
import com.zhuravlevmikhail.a65appshomeproject.appManagers.LifecycleManager
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class AppHostActivity : AppCompatActivity(R.layout.activity_host) {

    private lateinit var pageManager: LifecycleManager
    
    private val navigator = SupportAppNavigator(this, R.id.fragmentsContainer)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageManager = App.instance.lifecycleManager
        if (savedInstanceState == null) {
            pageManager.onCreate(this)
            App.instance.cicerone.router.newRootScreen(ContactsScreen())
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.cicerone.navigatorHolder.setNavigator(navigator)
    }

    override fun onDestroy() {
        pageManager.onDestroy()
        super.onDestroy()
    }
}
