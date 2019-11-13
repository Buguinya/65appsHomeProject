package com.zhuravlevmikhail.a65appshomeproject.tests.presentation

import com.zhuravlevmikhail.a65appshomeproject.common.schedulersRX.TrampolineSchedulers
import com.zhuravlevmikhail.a65appshomeproject.core.ContactMapScreen
import com.zhuravlevmikhail.a65appshomeproject.domain.contacts.ContactsInteractor
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.DetailedPresenter
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.DetailedView
import com.zhuravlevmikhail.a65appshomeproject.mocks.ContactDetailedMock
import com.zhuravlevmikhail.a65appshomeproject.stubs.ContactsInteractorStub
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import ru.terrakok.cicerone.Router

class DetailedPresenterTest {

    private lateinit var detailedPresenter : DetailedPresenter
    private lateinit var contactInteractor: ContactsInteractor

    @Mock lateinit var router : Router
    @Mock lateinit var mockView : DetailedView

    @Before
    fun testSet() {
        MockitoAnnotations.initMocks(this)
        contactInteractor = ContactsInteractorStub()
        detailedPresenter = DetailedPresenter(
            contactInteractor,
            TrampolineSchedulers(),
            router)
        detailedPresenter.attachView(mockView)
    }

    @Test
    fun navigateToMapPageWhenViewRequire() {
        detailedPresenter.onLocationClicked(1)
        verify(router).navigateTo(any(ContactMapScreen::class.java))
    }

    @Test
    fun transferToViewContactWhenContactReceived() {
        detailedPresenter.onContactsPermissionApproved(1)
        verify(mockView).onReceivedContact(ContactDetailedMock.mock)
    }

    @Test
    fun showErrorIfContactMissed() {
        detailedPresenter.onContactsPermissionApproved(0)
        verify(mockView).showError("error")
    }
}