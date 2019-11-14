package com.zhuravlevmikhail.a65appshomeproject.diContainer.components

import com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.presentation.PresentersModule
import com.zhuravlevmikhail.a65appshomeproject.diContainer.scopes.FragmentScope
import com.zhuravlevmikhail.a65appshomeproject.fragments.mapGeneral.GenMapFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [PresentersModule::class])
interface GeneralMapComponent {
    fun inject(genMapFragment : GenMapFragment)
}