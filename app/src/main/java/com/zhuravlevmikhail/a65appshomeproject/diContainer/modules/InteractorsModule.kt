package com.zhuravlevmikhail.a65appshomeproject.diContainer.modules

import com.zhuravlevmikhail.a65appshomeproject.domain.contacts.ContactsRepository
import com.zhuravlevmikhail.a65appshomeproject.domain.contacts.ContactsInteractor
import com.zhuravlevmikhail.a65appshomeproject.domain.contacts.ContactsUseCase
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