package com.zhuravlevmikhail.a65appshomeproject.domain.contacts

import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.ContactGeneral
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.ContactDetailed
import io.reactivex.Single

interface ContactsInteractor {
    fun getContacts(name : String = "") : Single<List<ContactGeneral>>
    fun getDetailedContact(contactId : Long) : Single<ContactDetailed>
}
