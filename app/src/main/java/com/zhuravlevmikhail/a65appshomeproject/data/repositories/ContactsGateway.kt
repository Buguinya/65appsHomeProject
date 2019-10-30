package com.zhuravlevmikhail.a65appshomeproject.data.repositories

import com.zhuravlevmikhail.a65appshomeproject.data.api.contacts.ContactsProvider
import com.zhuravlevmikhail.a65appshomeproject.domain.contacts.ContactsRepository
import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.ContactGeneral
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.ContactDetailed
import io.reactivex.Single

class ContactsGateway(private val contactsProvider: ContactsProvider) :
    ContactsRepository {

    override fun getAllContacts(): Single<List<ContactGeneral>> =
        Single.fromCallable { contactsProvider.getAllContacts() }

    override fun getDetailedContact(contactId: Long): Single<ContactDetailed> =
        Single.fromCallable { contactsProvider.getDetailedContact(contactId) }

    override fun getAllQueriedContacts(name: String): Single<List<ContactGeneral>> =
        Single.fromCallable { contactsProvider.getRequestedContacts(name)  }

}