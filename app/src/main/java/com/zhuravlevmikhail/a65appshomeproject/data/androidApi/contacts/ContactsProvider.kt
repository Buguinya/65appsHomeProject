package com.zhuravlevmikhail.a65appshomeproject.data.androidApi.contacts

import com.zhuravlevmikhail.a65appshomeproject.domain.entities.contacts.ContactDetailed
import com.zhuravlevmikhail.a65appshomeproject.domain.entities.contacts.ContactGeneral

interface ContactsProvider {
    fun getDetailedContact(contactId : Long) : ContactDetailed?
    fun getAllContacts() : List<ContactGeneral>
    fun getRequestedContacts(name : String) : List<ContactGeneral>
}