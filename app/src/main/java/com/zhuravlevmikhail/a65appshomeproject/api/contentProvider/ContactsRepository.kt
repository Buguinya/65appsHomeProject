package com.zhuravlevmikhail.a65appshomeproject.api.contentProvider

import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.ContactGeneral
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.ContactDetailed
import io.reactivex.Single

interface ContactsRepository {
    fun getAllContacts() : Single<ArrayList<ContactGeneral>>
    fun getDetailedContact(contactId : Long) : Single<ContactDetailed>
}