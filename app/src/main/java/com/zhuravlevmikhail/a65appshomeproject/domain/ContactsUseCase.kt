package com.zhuravlevmikhail.a65appshomeproject.domain

import android.annotation.SuppressLint
import android.text.TextUtils
import com.zhuravlevmikhail.a65appshomeproject.common.Utils
import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.ContactGeneral
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.ContactDetailed
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.internal.operators.single.SingleInternalHelper.toObservable
import io.reactivex.rxkotlin.cast

class ContactsUseCase(private val contactsRepository: ContactsRepository) : ContactsInteractor {

    override fun getContacts(name : String): Single<List<ContactGeneral>> {
           return if (Utils.isTrimmedNotEmpty(name)) {
               contactsRepository.getAllQueriedContacts(name)
            } else {
               contactsRepository.getAllContacts()
            }
    }

    override fun getDetailedContact(contactId: Long): Single<ContactDetailed> {
        return contactsRepository.getDetailedContact(contactId)
    }
}