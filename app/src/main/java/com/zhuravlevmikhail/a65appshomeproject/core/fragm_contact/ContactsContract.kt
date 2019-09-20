package com.zhuravlevmikhail.a65appshomeproject.core.fragm_contact

import android.content.AsyncQueryHandler
import android.content.ContentResolver
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.MvpModel
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.MvpPresenter
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.MvpView
import io.reactivex.Observable

interface ContactsContract {

    interface TModel : MvpModel

    interface TPresenter<V : TView> : MvpPresenter<V> {
        fun queryContactsAsync(
            contentResolver: ContentResolver
        ): Observable<ArrayList<ContactsModel.ContactGeneral>>
    }

    interface TView : MvpView {
        fun onCameraAccessGranted()
        fun setContacts(newContacts: ArrayList<ContactsModel.ContactGeneral>)
        fun openDetailedContactPage(contactId: Long)
    }
}