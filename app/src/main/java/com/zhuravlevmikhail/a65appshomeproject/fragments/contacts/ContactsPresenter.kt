package com.zhuravlevmikhail.a65appshomeproject.fragments.contacts

import com.zhuravlevmikhail.a65appshomeproject.api.contentProvider.ContactsRepository
import com.zhuravlevmikhail.a65appshomeproject.core.App
import com.zhuravlevmikhail.a65appshomeproject.core.DetailedContactScreen
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.BasePresenter
import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.ContactMvp.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ContactsPresenter(private val contactsRepository: ContactsRepository) :
    ContactsPresenterContract<ContactsViewContract>,
    BasePresenter<ContactsViewContract>(){

    private var disposable : Disposable? = null

    override fun queryContactsAsync() {
        disposable = contactsRepository.getAllContacts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                mvpView?.onContactsReceived(result)
            }, {throwable ->
                mvpView?.showError(throwable.localizedMessage)
            })
    }

    override fun openDetailedContactFragment(contactId : Long) {
        App.instance.cicerone.router.navigateTo(DetailedContactScreen(contactId))
    }

    override fun detachView() {
        super.detachView()
        disposable?.dispose()
    }
}