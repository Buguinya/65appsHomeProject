package com.zhuravlevmikhail.a65appshomeproject.core.contacts

import android.content.ContentResolver
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.MvpModel
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.MvpPresenter
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.MvpView
import io.reactivex.Observable

interface ContactsContract {

    interface ContactsModelContract : MvpModel

    interface ContactsViewContract : MvpView {
        fun onContactsAccessGranted()
        fun setContacts(newContacts: ArrayList<ContactsModel.ContactGeneral>)
        fun openDetailedContactPage(contactId: Long)
    }

    interface ContactsPresenterContract<V : ContactsViewContract> : MvpPresenter<V> {
        fun queryContactsAsync(
            contentResolver: ContentResolver
        ): Observable<ArrayList<ContactsModel.ContactGeneral>>
    }
}