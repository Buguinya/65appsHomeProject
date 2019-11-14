package com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.presentation

import com.zhuravlevmikhail.a65appshomeproject.diContainer.scopes.ContactInfoScope
import com.zhuravlevmikhail.a65appshomeproject.domain.map.MapInteractor
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.innerFragments.MapPresenter
import dagger.Module
import dagger.Provides

@Module
class ContactInfoModule(private val contactId: Long) {

    @Provides
    @ContactInfoScope
    fun provideDetMapPresenter(mapInteractor: MapInteractor,
                               apiKey: String): MapPresenter {
        return MapPresenter(
            mapInteractor,
            apiKey,
            contactId
        )
    }
}