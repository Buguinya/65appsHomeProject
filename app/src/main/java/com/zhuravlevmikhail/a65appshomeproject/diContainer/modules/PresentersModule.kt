package com.zhuravlevmikhail.a65appshomeproject.diContainer.modules

import com.zhuravlevmikhail.a65appshomeproject.domain.ContactsInteractor
import com.zhuravlevmikhail.a65appshomeproject.diContainer.scopes.FragmentScope
import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.ContactsPresenter
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.DetailedPresenter
import dagger.Module
import dagger.Provides

@Module
class PresentersModule {

    @Provides
    @FragmentScope
    fun provideContactsPresenter(contactsInteractor: ContactsInteractor) : ContactsPresenter{
        return ContactsPresenter(contactsInteractor)
    }

    @Provides
    @FragmentScope
    fun provideDetailedPresenter(contactsInteractor: ContactsInteractor) : DetailedPresenter {
        return DetailedPresenter(contactsInteractor)
    }
}