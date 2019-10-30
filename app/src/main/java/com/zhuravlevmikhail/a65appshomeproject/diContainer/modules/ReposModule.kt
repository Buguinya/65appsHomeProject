package com.zhuravlevmikhail.a65appshomeproject.diContainer.modules

import com.zhuravlevmikhail.a65appshomeproject.data.androidApi.contacts.ContactsProvider
import com.zhuravlevmikhail.a65appshomeproject.data.androidApi.map.LocationProvider
import com.zhuravlevmikhail.a65appshomeproject.data.repositories.ContactsGateway
import com.zhuravlevmikhail.a65appshomeproject.data.repositories.MapGateway
import com.zhuravlevmikhail.a65appshomeproject.data.retrofit.GeoDecoder
import com.zhuravlevmikhail.a65appshomeproject.domain.contacts.ContactsRepository
import com.zhuravlevmikhail.a65appshomeproject.domain.map.MapRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ReposModule {

    @Provides
    @Singleton
    fun provideContactsRepo(contactsProvider : ContactsProvider) : ContactsRepository {
        return ContactsGateway(contactsProvider)
    }

    @Provides
    @Singleton
    fun provideMapRepo(mapProvider: LocationProvider, geoDecoder: GeoDecoder): MapRepository {
        return MapGateway(mapProvider, geoDecoder)
    }
}