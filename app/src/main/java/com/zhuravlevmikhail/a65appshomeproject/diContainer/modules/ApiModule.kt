package com.zhuravlevmikhail.a65appshomeproject.diContainer.modules

import android.content.ContentResolver
import com.zhuravlevmikhail.a65appshomeproject.data.api.ContactsProvider
import com.zhuravlevmikhail.a65appshomeproject.data.api.ContactsProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun providerContactsProvider(contentResolver: ContentResolver) : ContactsProvider {
        return ContactsProviderImpl(contentResolver)
    }
}