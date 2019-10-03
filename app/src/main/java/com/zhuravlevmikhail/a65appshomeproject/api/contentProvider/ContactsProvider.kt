package com.zhuravlevmikhail.a65appshomeproject.api.contentProvider

import android.content.ContentResolver
import android.net.Uri
import android.provider.ContactsContract
import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.ContactGeneral
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.ContactDetailed
import io.reactivex.Single

class ContactsProvider(private val contentResolver: ContentResolver) : ContactsRepository {

    override fun getAllContacts(): Single<ArrayList<ContactGeneral>> =
        Single.fromCallable { getAllContacts(contentResolver) }

    override fun getDetailedContact(contactId: Long): Single<ContactDetailed> =
        Single.fromCallable { getDetailedContact(contentResolver, contactId) }

    private fun getDetailedContact(contentResolver: ContentResolver, contactId : Long) : ContactDetailed?{
        var contactDetailed : ContactDetailed? = null
        val contactCursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            arrayOf(
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.PHOTO_URI,
                ContactsContract.CommonDataKinds.Phone.NUMBER),
            String.format("%s = ?", ContactsContract.Contacts._ID),
            arrayOf(contactId.toString()),
            null)
        try {
            contactCursor?.let {
                val nameIndex = contactCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                val phoneIndex = contactCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                val photoIndex = contactCursor.getColumnIndex(ContactsContract.Contacts.PHOTO_URI)
                if (it.count > 0) {
                    it.moveToFirst()
                    val name = it.getString(nameIndex)
                    val phone = it.getString(phoneIndex)
                    val photoUri = it.getString(photoIndex)
                    var email : String? = "No email"
                    val emailCursor = contentResolver.query (
                        ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                        null,
                        String.format("%s = ?", ContactsContract.Contacts.DISPLAY_NAME),
                        arrayOf(name),
                        null
                    )
                    try {
                        emailCursor?.let { emailCursor ->
                            if (emailCursor.count > 0) {
                                val emailIndex =
                                    emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA)
                                emailCursor.moveToFirst()
                                email = emailCursor.getString(emailIndex)
                            }
                        }
                        contactDetailed = if (photoUri != null) {
                            ContactDetailed(name, phone, email, Uri.parse(photoUri))
                        } else {
                            ContactDetailed(name, phone, email)
                        }
                    } finally {
                        emailCursor?.close()
                    }
                }
            }
        } finally {
            contactCursor?.close()
        }
        return contactDetailed
    }

    private fun getAllContacts(contentResolver: ContentResolver) : ArrayList<ContactGeneral>{
        val contactsGeneral = ArrayList<ContactGeneral>()
        val contactsCursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null,
            null)

        try {
            contactsCursor?.let {
                val nameIndex = contactsCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                val phoneIndex = contactsCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                val idIndex = it.getColumnIndexOrThrow(ContactsContract.Contacts._ID)
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