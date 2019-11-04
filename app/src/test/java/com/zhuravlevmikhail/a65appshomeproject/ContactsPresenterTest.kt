package com.zhuravlevmikhail.a65appshomeproject

import com.zhuravlevmikhail.a65appshomeproject.common.schedulersRX.TrampolineSchedulers
import com.zhuravlevmikhail.a65appshomeproject.domain.contacts.ContactsInteractor
import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.ContactGeneral
import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.ContactsPresenter
import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.ContactsView
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.ContactDetailed
import com.zhuravlevmikhail.a65appshomeproject.rules.SchedulersRule
import com.zhuravlevmikhail.a65appshomeproject.stubs.ContactsInteractorStub
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import ru.terrakok.cicerone.Router

class ContactsPresenterTest {

    private lateinit var contactsPresenter : ContactsPresenter
    private lateinit var contactsInteractor: ContactsInteractor

    @get:Rule public val schedulersRule = SchedulersRule()

    @Mock lateinit var router : Router
    @Mock lateinit var mockView : ContactsView

    @Before
    fun testSet() {
        MockitoAnnotations.initMocks(this)
        contactsInteractor = ContactsInteractorStub()
        contactsPresenter = ContactsPresenter(contactsInteractor, TrampolineSchedulers())

        RxJavaPlugins.setInitIoSchedulerHandler { Schedulers.trampoline()}
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun returnContactsWhenContactsProvided() {
        contactsPresenter.onContactsAccessGranted()
        val contacts = listOf(
            ContactGeneral(
                1, "Test", "8 888 888 88 88"
            )
        )
        (contactsInteractor as ContactsInteractorStub)
            .fetchContacts(contacts)
        contactsInteractor
            .getContacts()
            .test()
            .assertSubscribed()
            .assertNoErrors()
            .assertComplete()
            .assertValue(contacts)
            .dispose()
    }
}