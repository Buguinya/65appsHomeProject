package com.zhuravlevmikhail.a65appshomeproject.core

import android.app.Activity
import android.app.Application
import com.zhuravlevmikhail.a65appshomeproject.appManagers.ContactsManager
import com.zhuravlevmikhail.a65appshomeproject.appManagers.PageManager
import com.zhuravlevmikhail.a65appshomeproject.common.interfaces.LifecyclesForApp

class App :
    Application(),
    LifecyclesForApp {

    val pageManager = PageManager(this)

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