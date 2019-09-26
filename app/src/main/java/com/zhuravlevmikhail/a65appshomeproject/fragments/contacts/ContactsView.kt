package com.zhuravlevmikhail.a65appshomeproject.fragments.contacts

import android.content.pm.PackageManager
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhuravlevmikhail.a65appshomeproject.api.contentProvider.ContactsProvider
import com.zhuravlevmikhail.a65appshomeproject.appManagers.PermissionManager
import com.zhuravlevmikhail.a65appshomeproject.common.AppConst
import com.zhuravlevmikhail.a65appshomeproject.common.interfaces.ContactsClickListener
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.BaseFragmAndView
import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.ContactMvp.*
import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.recycler.ContactsAdapter
import kotlinx.android.synthetic.main.fragm_contacts_list.*

class ContactsView :
    ContactsViewContract,
    BaseFragmAndView<ContactsViewContract, ContactsPresenterContract<ContactsViewContract>>(){

    private var contactsAdapter: ContactsAdapter? = null

    override fun firstInit() {
        activity?.let {
            mvpPresenter = ContactsPresenter(ContactsProvider(it.contentResolver))
            mvpPresenter.attachView(this)
        }
    }

    override fun loadData() {
        this.onContactsAccessGranted()
    }

    override fun onContactsAccessGranted() {
        if (!PermissionManager.requestContactsPermission(this)) {
            configureContactsAdapter()
            mvpPresenter.queryContactsAsync()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == AppConst.PERMISSION_REQUEST_CODE_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                this.onContactsAccessGranted()
            }
        }
    }

    override fun onContactsReceived(contacts : ArrayList<ContactGeneral>) {
        setContacts(contacts)
    }

    override fun openDetailedContactPage(contactId : Long) {
        mvpPresenter.openDetailedContactFragment(contactId)
    }

    override fun freeView() {
        contactsAdapter = null
    }

    private fun configureContactsAdapter() {
        contactsAdapter =
            ContactsAdapter(
                contactsClickListener
            )
        val contactsLayoutManager = LinearLayoutManager(context)
        contactsList.adapter = contactsAdapter
        contactsList.layoutManager = contactsLayoutManager
    }

    private fun setContacts(newContacts : ArrayList<ContactGeneral>) {
        contactsAdapter?.setContacts(newContacts)
    }

    private val contactsClickListener = object : ContactsClickListener {
        override fun onClick(view: View, position: Int) {
            val id = contactsAdapter?.contacts?.get(position)?.id
            id?.let {
                openDetailedContactPage(id)
            }
        }
    }
}