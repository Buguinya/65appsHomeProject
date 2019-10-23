package com.zhuravlevmikhail.a65appshomeproject.fragments.contacts

import com.zhuravlevmikhail.a65appshomeproject.api.contentProvider.ContactsRepository
import com.zhuravlevmikhail.a65appshomeproject.common.Utils
import com.zhuravlevmikhail.a65appshomeproject.core.App
import com.zhuravlevmikhail.a65appshomeproject.core.DetailedContactScreen
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class ContactsPresenter(private val contactsRepository: ContactsRepository) : MvpPresenter<ContactsView>() {

    private val compositeDisposable = CompositeDisposable()
    private var queryContactDisposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.checkContactsAccess()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
        queryContactDisposables.dispose()
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

    fun onQueryChanged(query : String?) {
        if (queryContactDisposables.isDisposed) {
            queryContactDisposables = CompositeDisposable()
        }
        if (query != null && Utils.isTrimmedNotEmpty(query)) {
            this.queryContactsByName(query)
        } else {
            this.onQueryDeleted()
        }
    }

    private fun openDetailedContactFragment(contactId : Long) {
        App.instance.cicerone.router.navigateTo(DetailedContactScreen(contactId))
    }            

    private fun queryContactsByName(name : String) {
        (contactsRepository.getAllQueriedContacts(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.showProgress(true)}
                .doAfterTerminate { queryContactDisposables.dispose()}
                .subscribe ( { result ->
                    viewState.onContactsReceived(result)
                    viewState.showProgress(false)
                }, {
                    viewState.showError(it.localizedMessage)
                    viewState.showProgress(false)
                }) 
            ).addTo(queryContactDisposables)
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