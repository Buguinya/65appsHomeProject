package com.zhuravlevmikhail.a65appshomeproject.domain

import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.ContactGeneral
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.ContactDetailed
import io.reactivex.Single

interface ContactsRepository {
    fun getAllContacts(): Single<List<ContactGeneral>>
    fun getDetailedContact(contactId: Long): Single<ContactDetailed>
    fun getAllQueriedContacts(name: String): Single<List<ContactGeneral>>
}