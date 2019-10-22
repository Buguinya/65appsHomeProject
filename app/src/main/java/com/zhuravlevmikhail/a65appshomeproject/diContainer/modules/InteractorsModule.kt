package com.zhuravlevmikhail.a65appshomeproject.diContainer.modules

import android.content.ContentResolver
import com.zhuravlevmikhail.a65appshomeproject.data.api.ContactsProviderImpl
import com.zhuravlevmikhail.a65appshomeproject.data.repositories.ContactsRepository
import com.zhuravlevmikhail.a65appshomeproject.domain.ContactsInteractor
import com.zhuravlevmikhail.a65appshomeproject.domain.ContactsUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorsModule() {

    @Provides
    @Singleton
    fun provideContactsRepository(contactsRepository: ContactsRepository) : ContactsInteractor =
        ContactsUseCase(contactsRepository)
}