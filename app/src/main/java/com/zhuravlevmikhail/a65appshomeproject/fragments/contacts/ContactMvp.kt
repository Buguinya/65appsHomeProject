package com.zhuravlevmikhail.a65appshomeproject.fragments.contacts

import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.MvpPresenter
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.MvpView

interface ContactMvp {

    interface ContactsViewContract : MvpView {
        fun onContactsAccessGranted()
        fun openDetailedContactPage(contactId: Long)
        fun onContactsReceived(contacts: ArrayList<ContactGeneral>)
    }

    interface ContactsPresenterContract<V : ContactsViewContract> : MvpPresenter<V> {
        fun queryContactsAsync()
        fun openDetailedContactFragment(contactId: Long)
    }
}