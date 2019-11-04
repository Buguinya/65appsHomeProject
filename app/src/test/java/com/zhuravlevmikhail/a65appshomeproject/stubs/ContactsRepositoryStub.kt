package com.zhuravlevmikhail.a65appshomeproject.stubs

import com.zhuravlevmikhail.a65appshomeproject.domain.contacts.ContactsRepository
import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.ContactGeneral
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.ContactDetailed
import io.reactivex.Single
import java.util.*
import kotlin.collections.ArrayList

class ContactsRepositoryStub : ContactsRepository {

    private var contacts = Collections.emptyList<ContactGeneral>()

    override fun getAllQueriedContacts(name: String): Single<List<ContactGeneral>> {
        val queriedContacts = ArrayList<ContactGeneral>()
        contacts.forEach {contact ->
            if (contact.name == name) {
                queriedContacts.add(contact)
            }
        }
        return Single.fromCallable {
            Collections.checkedList(queriedContacts, ContactGeneral::class.java) }
    }

    override fun getAllContacts(): Single<List<ContactGeneral>> {
        return Single.fromCallable {
            Collections.checkedList(contacts, ContactGeneral::class.java)
        }
    }

    override fun getDetailedContact(contactId: Long): Single<ContactDetailed> {
        return Single.create { emitter ->
            if (emitter.isDisposed) {
                return@create
            }
            contacts.forEach {
                if (it.id == contactId) {
                    emitter.onSuccess(
                        ContactDetailed(
                            "Test", "8 888 888 88 88", "test@mail.ru"
                        )
                    )
                    return@create
                }
            }
            emitter.onError(Throwable("Error"))
        }
    }

    fun fetchContacts(contactsGeneral: List<ContactGeneral>) {
        contacts = contactsGeneral
    }
}
