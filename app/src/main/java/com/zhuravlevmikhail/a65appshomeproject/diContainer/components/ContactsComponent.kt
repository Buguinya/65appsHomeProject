package com.zhuravlevmikhail.a65appshomeproject.diContainer.components

import com.zhuravlevmikhail.a65appshomeproject.diContainer.scopes.FragmentScope
import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.ContactsFragment
import dagger.Component

@FragmentScope
@Component(
    modules = []
)
interface ContactsComponent {
    fun inject(contactsFragment: ContactsFragment)
}