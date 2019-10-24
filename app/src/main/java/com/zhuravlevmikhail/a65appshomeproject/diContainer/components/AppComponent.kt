package com.zhuravlevmikhail.a65appshomeproject.diContainer.components

import com.zhuravlevmikhail.a65appshomeproject.api.contentProvider.ContactsRepository
import com.zhuravlevmikhail.a65appshomeproject.core.App
import com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.AppModule
import com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.ReposModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = [
    AppModule::class,
    ReposModule::class])
interface AppComponent {
    fun provideContactsRepository() : ContactsRepository
    fun plusContactsComponent() : ContactsComponent
    fun plusDetailedContactComponent() : DetailedContactComponent
    fun plusMainComponent() : ActivityHostComponent
    fun inject(app : App)
}