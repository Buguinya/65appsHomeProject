package com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.presentation

import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.DetailedFragment
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.FRAGMENT_DATA_KEY_CONTACT_ID
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.innerFragments.UNKNOWN_CONTACT
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ContactInfoModule(private val contactsFragment: DetailedFragment) {

    @Provides
    @Named("contactId")
    fun provideContactId() : Long {
        return contactsFragment.arguments?.getLong(FRAGMENT_DATA_KEY_CONTACT_ID) ?: UNKNOWN_CONTACT
    }
}