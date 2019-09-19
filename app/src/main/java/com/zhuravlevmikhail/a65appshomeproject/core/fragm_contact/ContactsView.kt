package com.zhuravlevmikhail.a65appshomeproject.core.fragm_contact

import androidx.recyclerview.widget.LinearLayoutManager
import com.zhuravlevmikhail.a65appshomeproject.core.App
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.BaseFragmAndView
import kotlinx.android.synthetic.main.fragm_contacts_list.*

class ContactsView :
    ContactsContract.TView,
    BaseFragmAndView<ContactsModel, ContactsView, ContactsPresenter>(){

    private val _contactsManager = App.instance.contactsManager

    private lateinit var _contactsAdapter: ContactsAdapter

    override fun firstInit() {
        val mvpModel = ContactsModel()
        _mvpPresenter = ContactsPresenter(mvpModel)
        _mvpPresenter.attachView(this)
    }

    override fun lightInitViews() {

    }

    override fun loadData() {
        configureContactsAdapter()
        setContacts(_mvpPresenter.getAllContacts(activity!!.contentResolver))
    }

    override fun setContacts(newContacts : ArrayList<ContactsModel.ContactGeneral>) {
        _contactsAdapter.setContacts(newContacts)
    }

    override fun onCameraAccessGranted() {
        _contactsManager.requestCameraPermission(activity!!)
    }

    private fun configureContactsAdapter() {
        _contactsAdapter = ContactsAdapter()
        val contactsLayoutManager = LinearLayoutManager(context)
        contactsList.adapter = _contactsAdapter
        contactsList.layoutManager = contactsLayoutManager
    }
}