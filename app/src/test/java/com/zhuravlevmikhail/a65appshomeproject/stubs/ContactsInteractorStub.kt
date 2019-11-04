package com.zhuravlevmikhail.a65appshomeproject.stubs

import com.zhuravlevmikhail.a65appshomeproject.domain.contacts.ContactsInteractor
import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.ContactGeneral
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.ContactDetailed
import io.reactivex.Single
import java.util.*

class ContactsInteractorStub : ContactsInteractor {

    private val contacts = Collections.emptyList<ContactGeneral>()

    override fun getContacts(name: String): Single<List<ContactGeneral>> {
        return Single.fromCallable {
            Collections.checkedList(contacts, ContactGeneral::class.java)
        }
    }

    override fun getDetailedContact(contactId: Long): Single<ContactDetailed> {
        return Single.fromCallable {
            return@fromCallable ContactDetailed (
                "Test", "8 888 888 88 88", "test@mail.ru")
        }
    }
}