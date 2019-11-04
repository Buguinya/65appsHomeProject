package com.zhuravlevmikhail.a65appshomeproject

import com.zhuravlevmikhail.a65appshomeproject.domain.contacts.ContactsInteractor
import com.zhuravlevmikhail.a65appshomeproject.domain.contacts.ContactsRepository
import com.zhuravlevmikhail.a65appshomeproject.domain.contacts.ContactsUseCase
import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.ContactGeneral
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.ContactDetailed
import com.zhuravlevmikhail.a65appshomeproject.stubs.ContactsRepositoryStub
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

class ContactsInteractorTest {

    private lateinit var contactsRepository : ContactsRepository
    private lateinit var contactsInteractor : ContactsInteractor

    @Before
    fun testSet() {
        contactsRepository = ContactsRepositoryStub()
        contactsInteractor = ContactsUseCase(contactsRepository)
    }

    @Test
    fun returnContactsListAnyway() {
        val testObserver = TestObserver.create<List<ContactGeneral>>()
        contactsInteractor.getContacts().subscribe(testObserver)
        testObserver
            .assertSubscribed()
            .assertComplete()
            .assertNoErrors()
            .dispose()
    }

    @Test
    fun returnContactsLIstByName() {
        val testObserver = TestObserver.create<List<ContactGeneral>>()
        (contactsRepository as ContactsRepositoryStub)
            .fetchContacts(listOf(
                ContactGeneral(1, "Test", "")
            ))
        contactsInteractor.getContacts("Test").subscribe(testObserver)
        testObserver
            .assertSubscribed()
            .assertComplete()
            .assertNoErrors()
            .dispose()
    }

    @Test
    fun returnContactByOKId() {
        val testObserver = TestObserver.create<ContactDetailed>()
        val contact = ContactGeneral(1, "", "")
        (contactsRepository as ContactsRepositoryStub)
            .fetchContacts(listOf(contact))
        contactsInteractor.getDetailedContact(1).subscribe(testObserver)
        testObserver
            .assertSubscribed()
            .assertNoErrors()
            .assertComplete()
            .dispose()
    }

    @Test
    fun returnErrorByInvalidContactId() {
        val testObserver = TestObserver.create<ContactDetailed>()
        val contact = ContactGeneral(1, "", "")
        (contactsRepository as ContactsRepositoryStub)
            .fetchContacts(listOf(contact))
        contactsInteractor.getDetailedContact(2).subscribe(testObserver)
        testObserver
            .assertSubscribed()
            .assertNoValues()
            .assertNotComplete()
            .assertError(Throwable::class.java)
            .dispose()
    }
}