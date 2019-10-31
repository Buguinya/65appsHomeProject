package com.zhuravlevmikhail.a65appshomeproject.diContainer.components

import com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.ui.PresentersModule
import com.zhuravlevmikhail.a65appshomeproject.diContainer.scopes.FragmentScope
import com.zhuravlevmikhail.a65appshomeproject.fragments.map.MapFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent( modules = [
    PresentersModule::class
])
interface MapComponent {
    fun inject(mapFragment: MapFragment)
}