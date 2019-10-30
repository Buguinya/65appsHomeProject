package com.zhuravlevmikhail.a65appshomeproject.diContainer.modules

import com.zhuravlevmikhail.a65appshomeproject.domain.contacts.ContactsRepository
import com.zhuravlevmikhail.a65appshomeproject.domain.contacts.ContactsInteractor
import com.zhuravlevmikhail.a65appshomeproject.domain.contacts.ContactsUseCase
import com.zhuravlevmikhail.a65appshomeproject.domain.map.MapInteractor
import com.zhuravlevmikhail.a65appshomeproject.domain.map.MapRepository
import com.zhuravlevmikhail.a65appshomeproject.domain.map.MapUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorsModule() {

    @Provides
    @Singleton
    fun provideContactsRepository(contactsRepository: ContactsRepository) : ContactsInteractor =
        ContactsUseCase(contactsRepository)

    @Provides
    @Singleton
    fun provideMapInteractor(mapRepository: MapRepository): MapInteractor =
        MapUseCase(mapRepository)
}