package com.zhuravlevmikhail.a65appshomeproject.diContainer.components

import com.zhuravlevmikhail.a65appshomeproject.domain.contacts.ContactsInteractor
import com.zhuravlevmikhail.a65appshomeproject.core.App
import com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = [
    AppModule::class,
    ReposModule::class,
    ApiModule::class,
    InteractorsModule::class,
    LocationModule::class])
interface AppComponent {
    fun plusContactsComponent() : ContactsComponent
    fun plusDetailedContactComponent() : DetailedContactComponent
    fun plusMainComponent() : ActivityHostComponent
    fun plusMapComponent(): MapComponent
    fun inject(app : App)
}