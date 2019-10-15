package com.zhuravlevmikhail.a65appshomeproject.diContainer

import com.zhuravlevmikhail.a65appshomeproject.core.App
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = [AppModule::class])
interface AppComponent {
    fun inject(app : App)
}