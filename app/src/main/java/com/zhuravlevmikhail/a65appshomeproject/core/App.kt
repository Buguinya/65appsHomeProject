package com.zhuravlevmikhail.a65appshomeproject.core

import android.app.Activity
import android.app.Application
import com.zhuravlevmikhail.a65appshomeproject.common.LifecyclesForApp

class App :
    Application(),
    LifecyclesForApp {

    companion object {
        lateinit var instance: App
            private set
    }

    init {
        instance = this
    }

    override fun onActivityCreate(activity: Activity) {

    }

    override fun onActivityDestroy() {
    }
}