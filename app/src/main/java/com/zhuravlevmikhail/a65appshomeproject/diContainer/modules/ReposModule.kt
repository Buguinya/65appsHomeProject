package com.zhuravlevmikhail.a65appshomeproject.diContainer.modules

import com.zhuravlevmikhail.a65appshomeproject.data.api.ContactsProvider
import com.zhuravlevmikhail.a65appshomeproject.data.repositories.ContactsGateway
import com.zhuravlevmikhail.a65appshomeproject.domain.ContactsRepository
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
}