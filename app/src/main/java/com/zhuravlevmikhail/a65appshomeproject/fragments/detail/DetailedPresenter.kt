package com.zhuravlevmikhail.a65appshomeproject.fragments.detail

import com.zhuravlevmikhail.a65appshomeproject.api.contentProvider.ContactsRepository
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.BasePresenter
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.DetailedMvp.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DetailedPresenter(private val contactsRepository: ContactsRepository) :
        DetailedPresenterContract<DetailedViewContract>,
        BasePresenter<DetailedViewContract>() {

    private var disposable: Disposable? = null

    override fun queryContactAsync(contactId: Long) {
        disposable = contactsRepository.getDetailedContact(contactId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                mvpView?.onReceivedContact(result)
            }, { throwable ->
                mvpView?.showError(throwable.localizedMessage)
            })
    }

    override fun detachView() {
        super.detachView()
        disposable?.dispose()
    }
}