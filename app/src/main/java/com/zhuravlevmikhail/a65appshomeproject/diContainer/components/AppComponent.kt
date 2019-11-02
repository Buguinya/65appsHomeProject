package com.zhuravlevmikhail.a65appshomeproject.diContainer.components

import com.zhuravlevmikhail.a65appshomeproject.core.App
import com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.*
import com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.data.*
import com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.domain.InteractorsModule
import com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.domain.ReposModule
import com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.presentation.ContactInfoModule
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.DetailedFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = [
    AppModule::class,
    ReposModule::class,
    DataModule::class,
    InteractorsModule::class,
    LocationModule::class,
    NetworkModule::class,
    DBModule::class])
interface AppComponent {
    fun plusContactsComponent() : ContactsComponent
    fun plusDetailedContactComponent() : DetailedContactComponent
    fun plusMainComponent() : ActivityHostComponent
    fun plusMapComponent(): MapComponent
    fun inject(app : App)
}