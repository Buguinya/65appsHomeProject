package com.zhuravlevmikhail.a65appshomeproject.core.fragm_con_detailed

import android.content.ContentResolver
import android.content.ContentUris
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.ContactsContract
import android.provider.MediaStore
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.BasePresenter
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.BufferedInputStream
import java.io.ByteArrayInputStream
import java.lang.Exception
import java.nio.Buffer
import android.provider.ContactsContract.CommonDataKinds.Email
import android.R




class DetailedPresenter(model: DetailedModel) :
        DetailedContract.TPresenter<DetailedView>,
        BasePresenter<DetailedView, DetailedModel>(model){

    override fun queryContactWithoutImageAsync(contentResolver: ContentResolver, contactId: Long): Observable<DetailedModel.ContactDetailed> =
        Observable.create { emitter: ObservableEmitter<DetailedModel.ContactDetailed> ->
            try {
                if (!emitter.isDisposed) {
                    val contact = getContactWithoutImage(contentResolver, contactId)
                    if (contact != null) {
                        emitter.onNext(contact)
                        emitter.onComplete()
                    }
                }
            } catch (ex: Exception) {
                emitter.onError(ex)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

 /*   override fun queryContactImageAsync(contentResolver: ContentResolver, contactId: Long) : Observable<Bitmap> =
        Observable.create { emitter: ObservableEmitter<Bitmap> ->
            try {
                if (!emitter.isDisposed) {
                    val image = queryContactInfo(contentResolver, contactId)
                    if (image != null) {
                        emitter.onNext(image)
                        emitter.onComplete()
                    }
                }
            } catch (ex : Exception) {
                emitter.onError(ex)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())*/

    private fun getContactWithoutImage(contentResolver: ContentResolver, contactId : Long) : DetailedModel.ContactDetailed? {
        var contactDetailed : DetailedModel.ContactDetailed? = null
        val contactsCursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
            arrayOf(contactId.toString()), null)

        contactsCursor?.let {
            val nameIndex = contactsCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            val phoneIndex = contactsCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            val emailIndex = contactsCursor.getColumnIndex(Email.DISPLAY_NAME)
            if (it.moveToFirst()) {
                val name = it.getString(nameIndex)
                val phone = it.getString(phoneIndex)
                val email = it.getString(emailIndex)
                contactDetailed = DetailedModel.ContactDetailed(name, phone, email)
            }
            contactsCursor.close()
        }

        val emails = contentResolver.query(
            Email.CONTENT_URI,
            null,
            Email.CONTACT_ID + " = " + contactId,
            null,
            null
        )
        while (emails.moveToNext()) {
            val email = emails.getString(emails.getColumnIndex(Email.DATA))
            contactDetailed?.email = email
            break
        }
        emails.close()
        return contactDetailed
    }

  /*  private fun getContactImage(contentResolver: ContentResolver, contactId : Long) : Bitmap? {
        val contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId)
        val imageUri = Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY)
        return  MediaStore.Images.Media
            .getBitmap(contentResolver,
                imageUri)
    }

    private fun queryContactInfo(contentResolver: ContentResolver, contactId : Long): Bitmap?{
        val c = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            ContactsContract.Data.RAW_CONTACT_ID + "=?",
             arrayOf(contactId.toInt().toString()),
            null
        )
        var idColumn: Int = 0
        var lookupKeyColumn: Int = 0
        var contactUri: Uri? = null
        c?.let {
            idColumn = it.getColumnIndex(ContactsContract.Contacts._ID)
            // Gets the LOOKUP_KEY index
            lookupKeyColumn = it.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY)
            // Gets a content URI for the contact
            contactUri = ContactsContract.Contacts.getLookupUri(
                it.getLong(idColumn),
                it.getString(lookupKeyColumn)
            )
        }
        return null
    }

    private fun queryContactImage(contentResolver: ContentResolver, contactId : Int): Bitmap? {
        val c = contentResolver.query(
            ContactsContract.Data.CONTENT_URI,
            arrayOf(ContactsContract.CommonDataKinds.Photo.PHOTO),
            ContactsContract.Data._ID + "=?",
            arrayOf(contactId.toString()),
            null
        )
        var imageBytes: ByteArray? = null
        if (c != null) {
            if (c.moveToFirst()) {
                imageBytes = c.getBlob(0)
            }
            c.close()
        }

        return if (imageBytes != null) {
            BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        } else {
            null
        }
    }

*/
}