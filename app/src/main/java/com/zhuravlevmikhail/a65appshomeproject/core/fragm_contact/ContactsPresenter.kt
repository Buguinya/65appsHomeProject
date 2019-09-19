package com.zhuravlevmikhail.a65appshomeproject.core.fragm_contact

import android.content.ContentResolver
import android.provider.ContactsContract.*
import com.zhuravlevmikhail.a65appshomeproject.core.fragm_contact.ContactsModel.*
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.BasePresenter
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.Exception


class ContactsPresenter(model: ContactsModel) :
    ContactsContract.TPresenter<ContactsView>,
    BasePresenter<ContactsView, ContactsModel>(model){

    override fun queryContactsAsync(contentResolver: ContentResolver) : Observable<ArrayList<ContactGeneral>> =
        Observable.create {emitter: ObservableEmitter<ArrayList<ContactGeneral>> ->
            try {
                if (!emitter.isDisposed) {
                    val contacts = getAllContacts(contentResolver)
                    emitter.onNext(contacts)
                    emitter.onComplete()
                }
            } catch (ex : Exception) {
                emitter.onError(ex)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    private fun getAllContacts(contentResolver: ContentResolver) : ArrayList<ContactGeneral> {
        val contactsGeneral = ArrayList<ContactGeneral>()
        val contactsCursor = contentResolver.query(
            CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null,
            null)

        contactsCursor?.let {
            val nameIndex = contactsCursor.getColumnIndex(CommonDataKinds.Phone.DISPLAY_NAME)
            val phoneIndex = contactsCursor.getColumnIndex(CommonDataKinds.Phone.NUMBER)
            val idIndex = it.getColumnIndex(Contacts._ID)
            while (it.moveToNext()) {
                val id = it.getString(idIndex)
                val name = it.getString(nameIndex)
                val phone = it.getString(phoneIndex)
                contactsGeneral.add(
                    ContactGeneral(id, name, phone)
                )
            }
            contactsCursor.close()
        }
        return contactsGeneral
    }
}