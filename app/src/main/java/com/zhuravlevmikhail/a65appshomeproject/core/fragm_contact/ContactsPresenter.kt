package com.zhuravlevmikhail.a65appshomeproject.core.fragm_contact

import android.content.ContentResolver
import android.provider.ContactsContract.*
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.BasePresenter


class ContactsPresenter(model: ContactsModel) :
    ContactsContract.TPresenter<ContactsView>,
    BasePresenter<ContactsView, ContactsModel>(model){


    override fun getAllContacts(contentResolver: ContentResolver) : ArrayList<ContactsModel.ContactGeneral> {
        val contactsGeneral = ArrayList<ContactsModel.ContactGeneral>()
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
                    ContactsModel.ContactGeneral(id, name, phone)
                )
            }
        }
        return contactsGeneral
    }
}