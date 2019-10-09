package com.zhuravlevmikhail.a65appshomeproject.fragments.contacts

import com.zhuravlevmikhail.a65appshomeproject.api.contentProvider.ContactsRepository
import com.zhuravlevmikhail.a65appshomeproject.core.App
import com.zhuravlevmikhail.a65appshomeproject.core.DetailedContactScreen
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class ContactsPresenter(private val contactsRepository: ContactsRepository) : MvpPresenter<ContactsView>() {

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
        openDetailedContactFragment(id)
    }

    fun onQueryChanged(query : String) {
        compositeDisposable
            .add(contactsRepository.getAllQueredContacts(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.showProgress(true)}
                .subscribe ( { result ->
                    viewState.onContactsReceived(result)
                }, {
                    viewState.showError(it.localizedMessage)
                })
            )
    }

    private fun openDetailedContactFragment(contactId : Long) {
        App.instance.cicerone.router.navigateTo(DetailedContactScreen(contactId))
    }

    private fun queryContactsAsync() {
        compositeDisposable
            .add(contactsRepository.getAllContacts()
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