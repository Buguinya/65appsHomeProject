package com.zhuravlevmikhail.a65appshomeproject.diContainer.modules

import com.zhuravlevmikhail.a65appshomeproject.domain.ContactsRepository
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