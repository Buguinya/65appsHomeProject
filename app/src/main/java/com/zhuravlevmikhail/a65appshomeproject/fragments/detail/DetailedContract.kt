package com.zhuravlevmikhail.a65appshomeproject.fragments.detail

import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.MvpPresenter
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.MvpView

interface DetailedContract {

    interface DetailedViewContract : MvpView {
        fun onReceivedContact(contact: ContactDetailed)
    }

    interface DetailedPresenterContract<V : DetailedViewContract> : MvpPresenter<V> {
        fun queryContactAsync(contactId: Long)
    }
}