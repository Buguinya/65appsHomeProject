package com.zhuravlevmikhail.a65appshomeproject.core.fragm_contact

import android.content.ContentResolver
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.MvpModel
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.MvpPresenter
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.MvpView

interface ContactsContract {

    interface TModel : MvpModel

    interface TPresenter<V : TView> : MvpPresenter<V> {
        fun getAllContacts(contentResolver: ContentResolver): ArrayList<ContactsModel.ContactGeneral>
    }

    interface TView : MvpView {
        fun onCameraAccessGranted()
        fun setContacts(newContacts: ArrayList<ContactsModel.ContactGeneral>)
    }
}