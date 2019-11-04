package com.zhuravlevmikhail.a65appshomeproject.stubs

import com.zhuravlevmikhail.a65appshomeproject.common.Utils
import com.zhuravlevmikhail.a65appshomeproject.domain.contacts.ContactsInteractor
import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.ContactGeneral
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.ContactDetailed
import io.reactivex.Single
import okhttp3.internal.Util
import java.util.*

class ContactsInteractorStub : ContactsInteractor {

    private var contacts = Collections.emptyList<ContactGeneral>()

    override fun getContacts(name: String): Single<List<ContactGeneral>> {
        return Single.fromCallable {
            if (Utils.isTrimmedNotEmpty(name))
                Collections.checkedList(contacts, ContactGeneral::class.java)
            else null
        }
    }

    override fun getDetailedContact(contactId: Long): Single<ContactDetailed> {
        return Single.fromCallable {
           if (contactId != 0.toLong()) ContactDetailed (
                "Test", "8 888 888 88 88", "test@mail.ru")
            else null
        }
    }

    fun fetchContacts(contacts: List<ContactGeneral>) {
        this.contacts = contacts
    }
}