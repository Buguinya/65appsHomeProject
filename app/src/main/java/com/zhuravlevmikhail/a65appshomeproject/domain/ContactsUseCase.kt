package com.zhuravlevmikhail.a65appshomeproject.domain

import android.annotation.SuppressLint
import com.zhuravlevmikhail.a65appshomeproject.data.repositories.ContactsRepository
import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.ContactGeneral
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.ContactDetailed
import io.reactivex.Single

class ContactsUseCase(private val contactsRepository: ContactsRepository) : ContactsInteractor {

    override fun getAllContacts(): Single<ArrayList<ContactGeneral>> {
        return contactsRepository.getAllContacts()
    }

    override fun getDetailedContact(contactId: Long): Single<ContactDetailed> {
        return contactsRepository.getDetailedContact(contactId)
    }

    @SuppressLint("DefaultLocale")
    override fun getAllQueriedContacts(name: String): Single<ArrayList<ContactGeneral>> {
        val lowerName = name.toLowerCase()
        return contactsRepository.getAllQueriedContacts(lowerName)
    }
}