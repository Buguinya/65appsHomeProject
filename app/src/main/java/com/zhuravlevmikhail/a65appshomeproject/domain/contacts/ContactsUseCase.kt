package com.zhuravlevmikhail.a65appshomeproject.domain.contacts

import com.zhuravlevmikhail.a65appshomeproject.common.Utils
import com.zhuravlevmikhail.a65appshomeproject.domain.entities.contacts.ContactDetailed
import com.zhuravlevmikhail.a65appshomeproject.domain.entities.contacts.ContactGeneral
import io.reactivex.Single

class ContactsUseCase(private val contactsRepository: ContactsRepository) :
    ContactsInteractor {

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