package com.zhuravlevmikhail.a65appshomeproject.core.fragm_contact

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhuravlevmikhail.a65appshomeproject.appManagers.ContactsManager
import com.zhuravlevmikhail.a65appshomeproject.common.interfaces.ContactsClickListener
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.BaseFragmAndView
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragm_contacts_list.*

class ContactsView :
    ContactsContract.TView,
    BaseFragmAndView<ContactsModel, ContactsView, ContactsPresenter>(){

    private var contactsAdapter: ContactsAdapter? = null
    private var disposable : Disposable? = null

    override fun firstInit() {
        val mvpModel = ContactsModel()
        _mvpPresenter = ContactsPresenter(mvpModel)
        _mvpPresenter.attachView(this)
    }

    override fun lightInitViews() {
    }

    override fun loadData() {
        this.onContactsAccessGranted()
    }

    override fun setContacts(newContacts : ArrayList<ContactsModel.ContactGeneral>) {
        contactsAdapter?.setContacts(newContacts)
    }

    override fun onContactsAccessGranted() {
        if (!ContactsManager.requestContactsPermission(activity!!)) {
            configureContactsAdapter()
            configureObserver()
        }
    }

    override fun openDetailedContactPage(contactId : Long) {
        pageManager.addDetailedContactPage(contactId)
    }

    private fun configureContactsAdapter() {
        contactsAdapter = ContactsAdapter(contactsClickListener)
        val contactsLayoutManager = LinearLayoutManager(context)
        contactsList.adapter = contactsAdapter
        contactsList.layoutManager = contactsLayoutManager
    }

    private fun configureObserver() {
        disposable = _mvpPresenter.queryContactsAsync(activity!!.contentResolver)
            .subscribe ({result ->
                setContacts(result)
            }, {
                showSnackbar(it.localizedMessage)
            })
    }

    override fun freeView() {
        contactsAdapter = null
        disposable?.dispose()
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