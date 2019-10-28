package com.zhuravlevmikhail.a65appshomeproject.diContainer.components

import com.zhuravlevmikhail.a65appshomeproject.domain.contacts.ContactsInteractor
import com.zhuravlevmikhail.a65appshomeproject.core.App
import com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.AppModule
import com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.InteractorsModule
import com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.ApiModule
import com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.ReposModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = [
    AppModule::class,
    ReposModule::class,
    ApiModule::class,
    InteractorsModule::class])
interface AppComponent {
    fun provideContactsRepository() : ContactsInteractor
    fun plusContactsComponent() : ContactsComponent
    fun plusDetailedContactComponent() : DetailedContactComponent
    fun plusMainComponent() : ActivityHostComponent
    fun inject(app : App)
}