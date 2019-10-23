package com.zhuravlevmikhail.a65appshomeproject.fragments.contacts

import com.zhuravlevmikhail.a65appshomeproject.domain.ContactsInteractor
import com.zhuravlevmikhail.a65appshomeproject.core.App
import com.zhuravlevmikhail.a65appshomeproject.core.DetailedContactScreen
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class ContactsPresenter @Inject constructor(private val contactsInteractor: ContactsInteractor) :
    MvpPresenter<ContactsView>() {

    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.checkContactsAccess()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    fun onContactsAccessGranted() {
        this.queryContactsAsync()
    }

    fun onQueryDeleted() {
        this.queryContactsAsync()
    }

    fun onContactClicked(id : Long) {
        this.openDetailedContactFragment(id)
    }

    fun onQueryChanged(query : String) {
        this.queryContactsAsync(query)
    }

    private fun openDetailedContactFragment(contactId : Long) {
        App.instance.cicerone.router.navigateTo(DetailedContactScreen(contactId))
    }            

    private fun queryContactsAsync(name : String = "") {
        compositeDisposable
            .add(contactsInteractor.getContacts(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.showProgress(true)}
                .subscribe({ result ->
                    viewState.onContactsReceived(result)
                    viewState.showProgress(false)
                }, {throwable ->
                    viewState.showError(throwable.localizedMessage)
                    viewState.showProgress(false)
                })
            )
    }
}