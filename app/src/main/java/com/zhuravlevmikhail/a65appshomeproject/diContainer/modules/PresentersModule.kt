package com.zhuravlevmikhail.a65appshomeproject.diContainer.modules

import com.zhuravlevmikhail.a65appshomeproject.api.contentProvider.ContactsRepository
import com.zhuravlevmikhail.a65appshomeproject.diContainer.scopes.FragmentScope
import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.ContactsPresenter
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.DetailedPresenter
import dagger.Module
import dagger.Provides

@Module
class PresentersModule {

    @Provides
    @FragmentScope
    fun provideContactsPresenter(contactsRepository: ContactsRepository) : ContactsPresenter{
        return ContactsPresenter(contactsRepository)
    }

    @Provides
    @FragmentScope
    fun provideDetailedPresenter(contactsRepository: ContactsRepository) : DetailedPresenter {
        return DetailedPresenter(contactsRepository)
    }
}