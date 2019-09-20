package com.zhuravlevmikhail.a65appshomeproject.core.fragm_contact

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhuravlevmikhail.a65appshomeproject.common.interfaces.ContactsClickListener
import com.zhuravlevmikhail.a65appshomeproject.core.App
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.BaseFragmAndView
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragm_contacts_list.*

class ContactsView :
    ContactsContract.TView,
    BaseFragmAndView<ContactsModel, ContactsView, ContactsPresenter>(){

    private val _contactsManager = App.instance.contactsManager

    private var _contactsAdapter: ContactsAdapter? = null
    private var _disposable : Disposable? = null

    override fun firstInit() {
        val mvpModel = ContactsModel()
        _mvpPresenter = ContactsPresenter(mvpModel)
        _mvpPresenter.attachView(this)
    }

    override fun lightInitViews() {
    }

    override fun loadData() {
        configureContactsAdapter()
        configureObserver()
    }

    override fun setContacts(newContacts : ArrayList<ContactsModel.ContactGeneral>) {
        _contactsAdapter?.setContacts(newContacts)
    }

    override fun onCameraAccessGranted() {
        _contactsManager.requestCameraPermission(activity!!)
    }

    override fun openDetailedContactPage(contactId : Long) {
        _pageManager.addDetailedContactPage(contactId)
    }

    private fun configureContactsAdapter() {
        _contactsAdapter = ContactsAdapter(contactsClickListener)
        val contactsLayoutManager = LinearLayoutManager(context)
        contactsList.adapter = _contactsAdapter
        contactsList.layoutManager = contactsLayoutManager
    }

    private fun configureObserver() {
        _disposable = _mvpPresenter.queryContactsAsync(activity!!.contentResolver)
            .subscribe ({result ->
                setContacts(result)
            }, {
                showSnackbar(it.localizedMessage)
            })
    }

    override fun freeView() {
        _contactsAdapter = null
        _disposable?.dispose()
    }

    private val contactsClickListener = object : ContactsClickListener {
        override fun onClick(view: View, id: Long) {
            openDetailedContactPage(id)
        }
    }
}