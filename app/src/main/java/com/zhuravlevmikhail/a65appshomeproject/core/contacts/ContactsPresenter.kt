package com.zhuravlevmikhail.a65appshomeproject.core.contacts

import android.content.ContentResolver
import android.provider.ContactsContract.*
import com.zhuravlevmikhail.a65appshomeproject.core.App
import com.zhuravlevmikhail.a65appshomeproject.core.DetailedContactScreen
import com.zhuravlevmikhail.a65appshomeproject.core.contacts.ContactsModel.*
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.BasePresenter
import io.reactivex.Single


class ContactsPresenter(model: ContactsModel) :
    ContactsContract.ContactsPresenterContract<ContactsView>,
    BasePresenter<ContactsView, ContactsModel>(model){

    override fun queryContactsAsync(contentResolver: ContentResolver): Single<ArrayList<ContactGeneral>> =
        Single.fromCallable { getAllContacts(contentResolver) }

    override fun openDetailedContactFragm(contactId : Long) {
        App.instance.cicerone.router.navigateTo(DetailedContactScreen(contactId))
    }

    private fun getAllContacts(contentResolver: ContentResolver) : ArrayList<ContactGeneral>{
        val contactsGeneral = ArrayList<ContactGeneral>()
        val contactsCursor = contentResolver.query(
            CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null,
            null)

        try {
            contactsCursor?.let {
                val nameIndex = contactsCursor.getColumnIndex(CommonDataKinds.Phone.DISPLAY_NAME)
                val phoneIndex = contactsCursor.getColumnIndex(CommonDataKinds.Phone.NUMBER)
                val idIndex = it.getColumnIndexOrThrow(Contacts._ID)
                while (it.moveToNext()) {
                    val id = it.getLong(idIndex)
                    val name = it.getString(nameIndex)
                    val phone = it.getString(phoneIndex)
                    contactsGeneral.add(
                        ContactGeneral(id, name, phone)
                    )
                }
            }
        } finally {
            contactsCursor?.close()
        }
        return contactsGeneral
    }
}