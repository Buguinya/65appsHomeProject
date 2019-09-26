package com.zhuravlevmikhail.a65appshomeproject.fragments.contacts

import android.content.ContentResolver
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.MvpPresenter
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.MvpView
import io.reactivex.Single

interface ContactsContract {

    interface ContactsViewContract : MvpView {
        fun onContactsAccessGranted()
        fun setContacts(newContacts: ArrayList<ContactGeneral>)
        fun openDetailedContactPage(contactId: Long)
    }

    interface ContactsPresenterContract<V : ContactsViewContract> : MvpPresenter<V> {
        fun queryContactsAsync(
            contentResolver: ContentResolver
        ): Single<ArrayList<ContactGeneral>>
        fun openDetailedContactFragm(contactId: Long)
    }
}