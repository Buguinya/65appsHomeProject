package com.zhuravlevmikhail.a65appshomeproject.fragments.detail

import android.content.ContentResolver
import android.net.Uri
import android.provider.ContactsContract.CommonDataKinds.*
import android.provider.ContactsContract.Contacts.*
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.BasePresenter
import io.reactivex.Single

class DetailedPresenter :
        DetailedContract.DetailedPresenterContract<DetailedView>,
        BasePresenter<DetailedView>(){

    override fun queryContactAsync(contentResolver: ContentResolver, contactId: Long): Single<ContactDetailed> =
        Single.fromCallable { getContact(contentResolver, contactId) }

    private fun getContact(contentResolver: ContentResolver, contactId : Long) : ContactDetailed?{
        var contactDetailed : ContactDetailed? = null
        val contactCursor = contentResolver.query(
                Phone.CONTENT_URI,
                arrayOf(
                        DISPLAY_NAME,
                        PHOTO_URI,
                        Phone.NUMBER,
                        Email.DISPLAY_NAME),
                String.format("%s = ?", _ID),
                arrayOf(contactId.toString()),
                null)
        try {
            contactCursor?.let {
                val nameIndex = contactCursor.getColumnIndex(Phone.DISPLAY_NAME)
                val phoneIndex = contactCursor.getColumnIndex(Phone.NUMBER)
                val emailIndex = contactCursor.getColumnIndex(Email.DISPLAY_NAME)
                val photoIndex = contactCursor.getColumnIndex(PHOTO_URI)
                if (it.count > 0) {
                    it.moveToFirst()
                    val name = it.getString(nameIndex)
                    val phone = it.getString(phoneIndex)
                    val email = it.getString(emailIndex)
                    val photoUri = it.getString(photoIndex)
                    contactDetailed = if (photoUri != null) {
                        ContactDetailed(name, phone, email, Uri.parse(photoUri))
                    } else {
                        ContactDetailed(name, phone, email)
                    }
                }
            }
        } finally {
            contactCursor?.close()
        }
        return contactDetailed
    }
}