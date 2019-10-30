package com.zhuravlevmikhail.a65appshomeproject.diContainer.modules

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.zhuravlevmikhail.a65appshomeproject.diContainer.scopes.FragmentScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocationModule {

    @Provides
    @Singleton
    fun provideLocationClient(context: Context) : FusedLocationProviderClient {
        return FusedLocationProviderClient(context)
    }
}