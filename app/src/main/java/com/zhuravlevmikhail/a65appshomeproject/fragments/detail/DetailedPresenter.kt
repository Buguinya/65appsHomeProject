package com.zhuravlevmikhail.a65appshomeproject.fragments.detail

import com.zhuravlevmikhail.a65appshomeproject.common.schedulersRX.SchedulersProvider
import com.zhuravlevmikhail.a65appshomeproject.core.App
import com.zhuravlevmikhail.a65appshomeproject.core.ContactMapScreen
import com.zhuravlevmikhail.a65appshomeproject.domain.contacts.ContactsInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Screen
import javax.inject.Inject

@InjectViewState
class DetailedPresenter @Inject
    constructor(private val contactsInteractor: ContactsInteractor,
                private val schedulersProvider: SchedulersProvider) :
    MvpPresenter<DetailedView>() {

    private var disposable: Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.requestContactsPermission()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

    fun onContactsPermissionApproved(contactId: Long) {
        queryContactAsync(contactId)
    }

    fun onLocationClicked(contactId: Long) {
        App.instance.cicerone.router.navigateTo(ContactMapScreen(contactId))
    }

    private fun queryContactAsync(contactId: Long) {
        disposable = contactsInteractor.getDetailedContact(contactId)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
            .subscribe({ result ->
                viewState.onReceivedContact(result)
            }, { throwable ->
                viewState.showError(throwable.localizedMessage)
            })
    }
}