package com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.presentation

import android.content.Context
import com.zhuravlevmikhail.a65appshomeproject.R
import com.zhuravlevmikhail.a65appshomeproject.diContainer.scopes.ContactInfoScope
import com.zhuravlevmikhail.a65appshomeproject.domain.map.MapInteractor
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.innerFragments.MapPresenter
import dagger.Module
import dagger.Provides

@Module
class ContactInfoModule(private val contactId: Long) {

    @Provides
    @ContactInfoScope
    fun provideMapPresenter(mapInteractor: MapInteractor, context: Context): MapPresenter {
        val apiKey = context.resources.getString(R.string.yandex_api_key)
        return MapPresenter(
            mapInteractor,
            apiKey,
            contactId
        )
    }
}