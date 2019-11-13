package com.zhuravlevmikhail.a65appshomeproject.diContainer.modules

import android.content.ContentResolver
import android.content.Context
import com.zhuravlevmikhail.a65appshomeproject.appManagers.LifecycleManager
import com.zhuravlevmikhail.a65appshomeproject.core.App
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class AppModule (private val app: App) {

    @Singleton
    @Provides
    fun provideContext() : Context {
        return app
    }

    @Singleton
    @Provides
    fun provideApp() : App {
        return app
    }

    @Singleton
    @Provides
    fun provideCicerone() : Cicerone<Router> {
        return Cicerone.create()
    }

    @Singleton
    @Provides
    fun provideContentResolver() : ContentResolver {
        return app.contentResolver
    }
}