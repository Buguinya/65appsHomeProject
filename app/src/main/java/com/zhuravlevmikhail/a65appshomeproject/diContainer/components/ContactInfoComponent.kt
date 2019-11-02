package com.zhuravlevmikhail.a65appshomeproject.diContainer.components

import com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.presentation.ContactInfoModule
import com.zhuravlevmikhail.a65appshomeproject.diContainer.scopes.ContactInfoScope
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.DetailedFragment
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.innerFragments.MapFragment
import dagger.Subcomponent

@ContactInfoScope
@Subcomponent(modules = [
        ContactInfoModule::class
    ])
interface ContactInfoComponent {
    fun inject(detailedFragment: DetailedFragment)
    fun inject(mapFragment : MapFragment)
}