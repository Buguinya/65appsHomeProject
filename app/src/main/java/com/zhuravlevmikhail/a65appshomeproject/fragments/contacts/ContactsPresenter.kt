package com.zhuravlevmikhail.a65appshomeproject.fragments.contacts

import com.zhuravlevmikhail.a65appshomeproject.api.contentProvider.ContactsRepository
import com.zhuravlevmikhail.a65appshomeproject.core.App
import com.zhuravlevmikhail.a65appshomeproject.core.DetailedContactScreen
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class ContactsPresenter(private val contactsRepository: ContactsRepository) : MvpPresenter<ContactsView>() {

    private var disposable : Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.askContactsPermisson()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

    fun queryContactsAsync() {
        disposable = contactsRepository.getAllContacts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                viewState.onContactsReceived(result)
            }, {throwable ->
                viewState.showError(throwable.localizedMessage)
            })
    }

    fun openDetailedContactFragment(contactId : Long) {
        App.instance.cicerone.router.navigateTo(DetailedContactScreen(contactId))
    }
}