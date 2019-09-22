package com.zhuravlevmikhail.a65appshomeproject.core.detail

import android.content.ContentResolver
import android.net.Uri
import android.provider.ContactsContract.CommonDataKinds.*
import android.provider.ContactsContract.Contacts.*
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.BasePresenter
import java.lang.Exception
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailedPresenter(model: DetailedModel) :
        DetailedContract.DetailedPresenterContract<DetailedView>,
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
                Phone.CONTENT_URI,
                arrayOf(
                        DISPLAY_NAME,
                        PHOTO_URI,
                        Phone.NUMBER,
                        Email.ADDRESS),
                String.format("%s = ?", _ID),
                arrayOf(contactId.toString()),
                null)
        try {
            contactsCursor?.let {
                val nameIndex = contactsCursor.getColumnIndex(Phone.DISPLAY_NAME)
                val phoneIndex = contactsCursor.getColumnIndex(Phone.NUMBER)
                val emailIndex = contactsCursor.getColumnIndex(Email.ADDRESS)
                val photoIndex = contactsCursor.getColumnIndex(PHOTO_URI)
                if (it.count > 0) {
                    it.moveToFirst()
                    val name = it.getString(nameIndex)
                    val phone = it.getString(phoneIndex)
                    val email = it.getString(emailIndex)
                    val photoUri = it.getString(photoIndex)
                    contactDetailed = if (photoUri != null) {
                        DetailedModel.ContactDetailed(name, phone, email, Uri.parse(photoUri))
                    } else {
                        DetailedModel.ContactDetailed(name, phone, email)
                    }
                }
            }
        } finally {
            contactsCursor?.close()
        }
        return contactDetailed
    }
}