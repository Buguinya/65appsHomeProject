package com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.data

import android.content.ContentResolver
import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute.Builder
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
import com.zhuravlevmikhail.a65appshomeproject.data.network.mapRouter.MapRouterMapboxService
import dagger.Module
import dagger.Provides
import javax.inject.Named
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
    @Named("yandex_key")
    fun provideYandexKey(app: App) : String {
        return app.resources.getString(R.string.yandex_api_key)
    }

    @Provides
    @Singleton
    fun provideMapRouter(builder: Builder) : MapRouter {
        return MapRouterMapboxService(builder)
    }

    @Provides
    @Singleton
    @Named("mapbox_key")
    fun provideMapboxKey(app: App) : String {
        return app.resources.getString(R.string.mapbox_api_key)
    }

    @Provides
    @Singleton
    fun provideNavigationRouteBuilder(context: Context,
                               @Named("mapbox_key") key: String) : Builder{
        return NavigationRoute.builder(context)
            .accessToken(key)
    }
}