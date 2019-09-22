package com.zhuravlevmikhail.a65appshomeproject.core.fragm_con_detailed

import android.content.ContentResolver
import android.provider.ContactsContract
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.BasePresenter
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import android.provider.ContactsContract.CommonDataKinds.Email

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

    private fun getContactWithoutImage(contentResolver: ContentResolver, contactId : Long) : DetailedModel.ContactDetailed?{
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
}