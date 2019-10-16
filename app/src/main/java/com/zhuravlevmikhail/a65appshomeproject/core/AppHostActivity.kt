package com.zhuravlevmikhail.a65appshomeproject.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zhuravlevmikhail.a65appshomeproject.R
import com.zhuravlevmikhail.a65appshomeproject.appManagers.LifecycleManager
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class AppHostActivity : AppCompatActivity(R.layout.activity_host) {

    @Inject lateinit var lifecycleManager: LifecycleManager
    @Inject lateinit var app : App
    
    private val navigator = SupportAppNavigator(this, R.id.fragmentsContainer)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.plusMainComponent().inject(this)
        if (savedInstanceState == null) {
            lifecycleManager.onCreate(this)
            app.cicerone.router.newRootScreen(ContactsScreen())
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        app.cicerone.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        app.cicerone.navigatorHolder.removeNavigator()
    }

    override fun onDestroy() {
        lifecycleManager.onDestroy()
        super.onDestroy()
    }
}
