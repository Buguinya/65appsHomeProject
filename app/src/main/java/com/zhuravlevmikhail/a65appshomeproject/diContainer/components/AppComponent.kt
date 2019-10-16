package com.zhuravlevmikhail.a65appshomeproject.diContainer.components

import com.zhuravlevmikhail.a65appshomeproject.core.App
import com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.AppModule
import com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.MainModule
import com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.ReposModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = [
    AppModule::class])
interface AppComponent {
    fun plusMainComponent() : ActivityHostComponent
    fun inject(app : App)
}