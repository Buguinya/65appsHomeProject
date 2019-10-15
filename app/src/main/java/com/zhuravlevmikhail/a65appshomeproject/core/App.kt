package com.zhuravlevmikhail.a65appshomeproject.core

import android.app.Activity
import android.app.Application
import com.zhuravlevmikhail.a65appshomeproject.appManagers.LifecycleManager
import com.zhuravlevmikhail.a65appshomeproject.common.interfaces.LifecycleForApp
import com.zhuravlevmikhail.a65appshomeproject.diContainer.AppComponent
import com.zhuravlevmikhail.a65appshomeproject.diContainer.DaggerAppComponent
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class App :
    Application(),
    LifecycleForApp {

    @Inject
    lateinit var lifecycleManager : LifecycleManager

    lateinit var appComponent : AppComponent
    lateinit var cicerone: Cicerone<Router>

    companion object {
        lateinit var instance: App
            private set
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        cicerone = Cicerone.create()
        DaggerAppComponent
            .builder()
            .build()
            .inject(this)
    }

    override fun onActivityCreate(activity: Activity) {}

    override fun onActivityDestroy() {}
}