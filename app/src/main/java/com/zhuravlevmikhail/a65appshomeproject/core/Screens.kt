package com.zhuravlevmikhail.a65appshomeproject.core

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.ContactsFragment
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.DetailedFragment
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.FRAGMENT_DATA_KEY_CONTACT_ID
import com.zhuravlevmikhail.a65appshomeproject.fragments.map.MapFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class ContactsScreen : SupportAppScreen() {

    override fun getFragment(): Fragment {
        return ContactsFragment()
    }
}

class DetailedContactScreen(private val contactId : Long) : SupportAppScreen() {

    override fun getFragment(): Fragment {
        val page = DetailedFragment()
        val args = Bundle()
        args.putLong(FRAGMENT_DATA_KEY_CONTACT_ID, contactId)
        page.arguments = args
        return page
    }
}

class ContactMapScreen(private val contactId: Long) : SupportAppScreen()  {

    override fun getFragment(): Fragment {
        return MapFragment()
    }
}