package com.zhuravlevmikhail.a65appshomeproject.domain.contacts

import com.zhuravlevmikhail.a65appshomeproject.common.Utils
import com.zhuravlevmikhail.a65appshomeproject.domain.contacts.ContactsInteractor
import com.zhuravlevmikhail.a65appshomeproject.domain.contacts.ContactsRepository
import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.ContactGeneral
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.ContactDetailed
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