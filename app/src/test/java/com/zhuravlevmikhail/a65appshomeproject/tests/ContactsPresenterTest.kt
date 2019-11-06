package com.zhuravlevmikhail.a65appshomeproject.tests

import com.zhuravlevmikhail.a65appshomeproject.common.schedulersRX.TrampolineSchedulers
import com.zhuravlevmikhail.a65appshomeproject.core.DetailedContactScreen
import com.zhuravlevmikhail.a65appshomeproject.domain.contacts.ContactsInteractor
import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.ContactsPresenter
import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.ContactsView
import com.zhuravlevmikhail.a65appshomeproject.mocks.ContactGeneralMock
import com.zhuravlevmikhail.a65appshomeproject.stubs.ContactsInteractorStub
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import ru.terrakok.cicerone.Router

class ContactsPresenterTest {

    private lateinit var contactsPresenter : ContactsPresenter
    private lateinit var contactsInteractor: ContactsInteractor

    private val testContact = ContactGeneralMock.mock

    @Mock lateinit var router : Router
    @Mock lateinit var mockView : ContactsView

    @Before
    fun testSet() {
        MockitoAnnotations.initMocks(this)
        contactsInteractor = ContactsInteractorStub()
        contactsPresenter = ContactsPresenter(contactsInteractor, TrampolineSchedulers(), router)
        contactsPresenter.attachView(mockView)
    }

    @Test
    fun returnContactsWhenContactsProvided() {
        val contacts = listOf(testContact)
        (contactsInteractor as ContactsInteractorStub)
            .fetchContacts(contacts)
        contactsPresenter.onContactsAccessGranted()
        verify(mockView).showProgress(true)
        verify(mockView).onContactsReceived(contacts)
        verify(mockView).showProgress(false)
    }

    @Test
    fun returnContactsWhenContactsProvidedByName() {
        val contacts = listOf(testContact)
        (contactsInteractor as ContactsInteractorStub)
            .fetchContacts(contacts)
        contactsPresenter.onQueryChanged("valid_name")
        verify(mockView).showProgress(true)
        verify(mockView).onContactsReceived(contacts)
        verify(mockView).showProgress(false)
    }

    @Test
    fun showAppropriateErrorWhenContactsMissed() {
        val contacts = listOf(testContact)
        (contactsInteractor as ContactsInteractorStub)
            .fetchContacts(contacts)
        contactsPresenter.onQueryChanged("test_wrong_name")
        verify(mockView).showProgress(true)
        verify(mockView).showError("no_contacts")
        verify(mockView).showProgress(false)
    }

    @Test
    fun navigateToContactPageWhenValidClicked() {
        contactsPresenter.onContactClicked(testContact.id)
        verify(router).navigateTo(ArgumentMatchers.any(DetailedContactScreen::class.java))
    }
}