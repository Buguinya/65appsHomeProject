package com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.data

import android.content.ContentResolver
import com.google.android.gms.location.FusedLocationProviderClient
import com.zhuravlevmikhail.a65appshomeproject.R
import com.zhuravlevmikhail.a65appshomeproject.core.App
import com.zhuravlevmikhail.a65appshomeproject.data.androidApi.contacts.ContactsProvider
import com.zhuravlevmikhail.a65appshomeproject.data.androidApi.contacts.ContactContentProvider
import com.zhuravlevmikhail.a65appshomeproject.data.androidApi.map.LocationProvider
import com.zhuravlevmikhail.a65appshomeproject.data.androidApi.map.LocationProviderImpl
import com.zhuravlevmikhail.a65appshomeproject.data.database.ContactDAO
import com.zhuravlevmikhail.a65appshomeproject.data.database.ContactsStorage
import com.zhuravlevmikhail.a65appshomeproject.data.database.ContactsStorageRoom
import com.zhuravlevmikhail.a65appshomeproject.data.network.geoDecoder.GeoDecoder
import com.zhuravlevmikhail.a65appshomeproject.data.network.geoDecoder.GeoDecoderYandex
import com.zhuravlevmikhail.a65appshomeproject.data.network.geoDecoder.YandexGeocodeApi
import com.zhuravlevmikhail.a65appshomeproject.data.network.mapRouter.MapRouter
import com.zhuravlevmikhail.a65appshomeproject.data.network.mapRouter.MapRouterApi
import com.zhuravlevmikhail.a65appshomeproject.data.network.mapRouter.MapRouterGoogleService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun providerContactsProvider(contentResolver: ContentResolver) : ContactsProvider {
        return ContactContentProvider(
            contentResolver
        )
    }

    @Provides
    @Singleton
    fun provideMapProvider(locationProviderClient: FusedLocationProviderClient): LocationProvider {
        return LocationProviderImpl(
            locationProviderClient
        )
    }

    @Provides
    @Singleton
    fun provideGeoDecoder(yandexGeocodeApi: YandexGeocodeApi): GeoDecoder {
        return GeoDecoderYandex(
            yandexGeocodeApi
        )
    }

    @Provides
    @Singleton
    fun provideContactsStorage(contactsDAO: ContactDAO) : ContactsStorage{
        return ContactsStorageRoom(contactsDAO)
    }

    @Provides
    @Singleton
    fun provideApiKey(app: App) : String {
        return app.resources.getString(R.string.yandex_api_key)
    }

    @Provides
    @Singleton
    fun provideMapRouter(mapRouterApi: MapRouterApi) : MapRouter {
        return MapRouterGoogleService(mapRouterApi)
    }
}