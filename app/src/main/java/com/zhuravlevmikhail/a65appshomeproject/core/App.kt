package com.zhuravlevmikhail.a65appshomeproject.core

import android.app.Activity
import android.app.Application
import com.zhuravlevmikhail.a65appshomeproject.appManagers.LifecycleManager
import com.zhuravlevmikhail.a65appshomeproject.common.interfaces.LifecycleForApp
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class App :
    Application(),
    LifecycleForApp {

    val lifecycleManager = LifecycleManager(this)
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
    }

    override fun onActivityCreate(activity: Activity) {}

    override fun onActivityDestroy() {}
}