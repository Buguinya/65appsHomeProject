package com.zhuravlevmikhail.a65appshomeproject.domain.contacts

import com.zhuravlevmikhail.a65appshomeproject.domain.entities.contacts.ContactDetailed
import com.zhuravlevmikhail.a65appshomeproject.domain.entities.contacts.ContactGeneral
import io.reactivex.Single

interface ContactsInteractor {
    fun getContacts(name : String = "") : Single<List<ContactGeneral>>
    fun getDetailedContact(contactId : Long) : Single<ContactDetailed>
}
