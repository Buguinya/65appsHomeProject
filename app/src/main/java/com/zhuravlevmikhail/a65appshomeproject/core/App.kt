package com.zhuravlevmikhail.a65appshomeproject.core

import android.app.Activity
import android.app.Application
import com.zhuravlevmikhail.a65appshomeproject.api.contentProvider.ContactsRepository
import com.zhuravlevmikhail.a65appshomeproject.appManagers.LifecycleManager
import com.zhuravlevmikhail.a65appshomeproject.common.interfaces.LifecycleForApp
import com.zhuravlevmikhail.a65appshomeproject.diContainer.components.AppComponent
import com.zhuravlevmikhail.a65appshomeproject.diContainer.components.DaggerAppComponent
import com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.AppModule
import com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.ReposModule
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class App :
    Application(),
    LifecycleForApp {
    
    @Inject lateinit var cicerone: Cicerone<Router>
    lateinit var appComponent : AppComponent

    companion object {
        lateinit var instance: App
            private set
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .reposModule(ReposModule(contentResolver))
            .build()
            .apply { inject(this@App) }
    }

    override fun onActivityCreate(activity: Activity) {}

    override fun onActivityDestroy() {}
}