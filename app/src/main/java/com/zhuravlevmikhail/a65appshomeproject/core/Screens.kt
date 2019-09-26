package com.zhuravlevmikhail.a65appshomeproject.core

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.zhuravlevmikhail.a65appshomeproject.R
import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.ContactsView
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.DetailedView
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.FRAGMENT_DATA_KEY_CONTACT_ID
import ru.terrakok.cicerone.android.support.SupportAppScreen

class ContactsScreen : SupportAppScreen() {

    override fun getFragment(): Fragment {
        val page = ContactsView()
        page.configure(R.layout.fragm_contacts_list, App.instance.lifecycleManager)
        return page
    }
}

class DetailedContactScreen(private val contactId : Long) : SupportAppScreen() {

    override fun getFragment(): Fragment {
        val page = DetailedView()
        val args = Bundle()
        args.putLong(FRAGMENT_DATA_KEY_CONTACT_ID, contactId)
        page.arguments = args
        page.configure(R.layout.fragm_con_detailed, App.instance.lifecycleManager)
        return page
    }
}