package com.zhuravlevmikhail.a65appshomeproject

import androidx.test.annotation.UiThreadTest
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.zhuravlevmikhail.a65appshomeproject.core.App
import com.zhuravlevmikhail.a65appshomeproject.core.AppHostActivity
import com.zhuravlevmikhail.a65appshomeproject.domain.entities.contacts.ContactGeneral
import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.ContactsFragment
import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.recycler.ContactHolder
import kotlinx.android.synthetic.main.fragm_contacts_list.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ContactsFragmentTest {

    @Rule
    @JvmField
    val activityRule =  object : ActivityTestRule<AppHostActivity>(AppHostActivity::class.java) {

        override fun beforeActivityLaunched() {
            val app = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as App
        }
    }

    private val contactsFragment = ContactsFragment()

    @Before
    fun testSet() {
        activityRule.launchActivity(null)
    }

    @UiThreadTest
    @Test
    fun testClickContact() {
        val contacts = ArrayList<ContactGeneral>()
        contacts.add(ContactGeneral(1, "7789", "8 888 888 88 88"))
        contacts.add(ContactGeneral(0, "6666", "7 777 777 77 77"))
        activityRule.runOnUiThread {
            contactsFragment.onContactsReceived(contacts)
        }

        onView(
            withId(R.id.contactsList)
        ).perform(RecyclerViewActions.actionOnItemAtPosition<ContactHolder>(0, click()))
        onView(
            withId(R.id.detContactName)
        ).check(matches(isDisplayed()))
    }
}