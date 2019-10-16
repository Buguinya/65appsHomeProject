package com.zhuravlevmikhail.a65appshomeproject.diContainer.components

import com.zhuravlevmikhail.a65appshomeproject.diContainer.scopes.FragmentScope
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.DetailedFragment
import dagger.Component

@FragmentScope
@Component( modules = [])
interface DetailedContactComponent {
    fun inject(detailedFragment: DetailedFragment)
}