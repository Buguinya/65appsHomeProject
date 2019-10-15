package com.zhuravlevmikhail.a65appshomeproject.diContainer

import android.content.Context
import com.zhuravlevmikhail.a65appshomeproject.appManagers.LifecycleManager
import com.zhuravlevmikhail.a65appshomeproject.core.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext() : Context {
        return App.instance.applicationContext
    }

    @Singleton
    @Provides
    fun provideLifecycleManager() : LifecycleManager {
        return LifecycleManager(App.instance)
    }
}