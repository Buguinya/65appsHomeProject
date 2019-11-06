package com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.presentation

import com.zhuravlevmikhail.a65appshomeproject.common.schedulersRX.CommonSchedulers
import com.zhuravlevmikhail.a65appshomeproject.common.schedulersRX.SchedulersProvider
import com.zhuravlevmikhail.a65appshomeproject.domain.contacts.ContactsInteractor
import com.zhuravlevmikhail.a65appshomeproject.diContainer.scopes.FragmentScope
import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.ContactsPresenter
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.DetailedPresenter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class PresentersModule {

    @Provides
    @FragmentScope
    fun provideContactsPresenter(contactsInteractor: ContactsInteractor,
                                 schedulersProvider: SchedulersProvider,
                                 router: Router) : ContactsPresenter{
        return ContactsPresenter(contactsInteractor, schedulersProvider, router)
    }

    @Provides
    @FragmentScope
    fun provideDetailedPresenter(contactsInteractor: ContactsInteractor,
                                 schedulersProvider: SchedulersProvider) : DetailedPresenter {
        return DetailedPresenter(contactsInteractor, schedulersProvider)
    }

    @Provides
    @FragmentScope
    fun provideSchedulers() : SchedulersProvider {
        return CommonSchedulers()
    }
}