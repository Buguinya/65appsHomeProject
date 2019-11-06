package com.zhuravlevmikhail.a65appshomeproject.stubs

import com.zhuravlevmikhail.a65appshomeproject.common.Utils
import com.zhuravlevmikhail.a65appshomeproject.domain.contacts.ContactsInteractor
import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.ContactGeneral
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.ContactDetailed
import com.zhuravlevmikhail.a65appshomeproject.mocks.ContactDetailedMock
import io.reactivex.Single
import okhttp3.internal.Util
import java.lang.Exception
import java.util.*

class ContactsInteractorStub : ContactsInteractor {

    private var contacts = Collections.emptyList<ContactGeneral>()

    override fun getContacts(name: String): Single<List<ContactGeneral>> {
        return Single.fromCallable {
            if (name != "test_wrong_name")
                Collections.checkedList(contacts, ContactGeneral::class.java)
            else throw Exception("no_contacts")
        }
    }

    override fun getDetailedContact(contactId: Long): Single<ContactDetailed> {
        return Single.fromCallable {
           if (contactId != 0.toLong()) ContactDetailedMock.mock
           else throw Exception("error")
        }
    }

    fun fetchContacts(contacts: List<ContactGeneral>) {
        this.contacts = contacts
    }
}