package com.zhuravlevmikhail.a65appshomeproject.fragments.detail

import com.zhuravlevmikhail.a65appshomeproject.api.contentProvider.ContactsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class DetailedPresenter(private val contactsRepository: ContactsRepository) :
    MvpPresenter<DetailedView>() {

    private var disposable: Disposable? = null

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

    fun queryContactAsync(contactId: Long) {
        disposable = contactsRepository.getDetailedContact(contactId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                viewState.onReceivedContact(result)
            }, { throwable ->
                viewState.showError(throwable.localizedMessage)
            })
    }
}