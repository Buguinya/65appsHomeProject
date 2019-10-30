package com.zhuravlevmikhail.a65appshomeproject.diContainer.modules

import android.content.ContentResolver
import com.google.android.gms.location.FusedLocationProviderClient
import com.zhuravlevmikhail.a65appshomeproject.data.api.contacts.ContactsProvider
import com.zhuravlevmikhail.a65appshomeproject.data.api.contacts.ContactsProviderImpl
import com.zhuravlevmikhail.a65appshomeproject.data.api.map.MapProvider
import com.zhuravlevmikhail.a65appshomeproject.data.api.map.MapProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun providerContactsProvider(contentResolver: ContentResolver) : ContactsProvider {
        return ContactsProviderImpl(
            contentResolver
        )
    }

    @Provides
    @Singleton
    fun provideMapProvider(locationProviderClient: FusedLocationProviderClient): MapProvider {
        return MapProviderImpl(
            locationProviderClient
        )
    }
}