package com.zhuravlevmikhail.a65appshomeproject.data.api

import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.ContactGeneral
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.ContactDetailed

interface ContactsProvider {
    fun getDetailedContact(contactId : Long) : ContactDetailed?
    fun getAllContacts() : List<ContactGeneral>
    fun getRequestedContacts(name : String) : List<ContactGeneral>
}