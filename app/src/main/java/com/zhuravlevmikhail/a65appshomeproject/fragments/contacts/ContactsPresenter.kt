package com.zhuravlevmikhail.a65appshomeproject.fragments.contacts

import com.zhuravlevmikhail.a65appshomeproject.common.Utils
import com.zhuravlevmikhail.a65appshomeproject.common.schedulersRX.SchedulersProvider
import com.zhuravlevmikhail.a65appshomeproject.domain.contacts.ContactsInteractor
import com.zhuravlevmikhail.a65appshomeproject.core.App
import com.zhuravlevmikhail.a65appshomeproject.core.DetailedContactScreen
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class ContactsPresenter
    @Inject constructor(private val contactsInteractor: ContactsInteractor,
                        private val schedulersProvider: SchedulersProvider) :
    MvpPresenter<ContactsView>() {

    private val compositeDisposable = CompositeDisposable()
    private var queryContactDisposable = Disposables.disposed()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.checkContactsAccess()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
        queryContactDisposable.dispose()
    }

    fun onContactsAccessGranted() {
        this.queryContactsAsync()
    }

    fun onContactClicked(id : Long) {
        this.openDetailedContactFragment(id)
    }

    fun onQueryChanged(query : String?) {
        if (query != null && Utils.isTrimmedNotEmpty(query)) {
            this.queryContactsAsync(query)
        } else {
            this.onQueryDeleted()
        }
    }

    private fun onQueryDeleted() {
        this.queryContactsAsync()
    }

    private fun openDetailedContactFragment(contactId : Long) {
        App.instance.cicerone.router.navigateTo(DetailedContactScreen(contactId))
    }            

    private fun queryContactsAsync(name : String = "") {
        compositeDisposable
            .add(contactsInteractor.getContacts(name)
                .subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
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