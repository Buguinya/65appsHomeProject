package com.zhuravlevmikhail.a65appshomeproject.core

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import com.zhuravlevmikhail.a65appshomeproject.R
import com.zhuravlevmikhail.a65appshomeproject.appManagers.LifecycleManager
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class AppHostActivity : AppCompatActivity(R.layout.activity_host) {

    private lateinit var lifecycleManager: LifecycleManager
    
    private val navigator = SupportAppNavigator(this, R.id.fragmentsContainer)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleManager = App.instance.lifecycleManager
        if (savedInstanceState == null) {
            lifecycleManager.onCreate(this)
            App.instance.cicerone.router.newRootScreen(ContactsScreen())
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.cicerone.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.cicerone.navigatorHolder.removeNavigator()
    }

    override fun onDestroy() {
        lifecycleManager.onDestroy()
        super.onDestroy()
    }
}
