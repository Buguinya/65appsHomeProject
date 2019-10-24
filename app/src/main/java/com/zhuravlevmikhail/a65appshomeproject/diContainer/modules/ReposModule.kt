package com.zhuravlevmikhail.a65appshomeproject.diContainer.modules

import android.content.ContentResolver
import com.zhuravlevmikhail.a65appshomeproject.api.contentProvider.ContactsProvider
import com.zhuravlevmikhail.a65appshomeproject.api.contentProvider.ContactsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ReposModule(private val contentResolver: ContentResolver) {

    @Provides
    @Singleton
    fun provideContactsRepository() : ContactsRepository =
        ContactsProvider(contentResolver)
}