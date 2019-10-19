package com.zhuravlevmikhail.a65appshomeproject.diContainer.components

import com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.PresentersModule
import com.zhuravlevmikhail.a65appshomeproject.diContainer.scopes.FragmentScope
import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.ContactsFragment
import dagger.Component
import dagger.Subcomponent

@FragmentScope
@Subcomponent(
    modules = [PresentersModule::class]
)
interface ContactsComponent {
    fun inject(contactsFragment: ContactsFragment)
}