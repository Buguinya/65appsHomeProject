package com.zhuravlevmikhail.a65appshomeproject.core.contacts

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhuravlevmikhail.a65appshomeproject.appManagers.ContactsManager
import com.zhuravlevmikhail.a65appshomeproject.common.interfaces.ContactsClickListener
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.BaseFragmAndView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragm_contacts_list.*

class ContactsView :
    ContactsContract.ContactsViewContract,
    BaseFragmAndView<ContactsModel, ContactsView, ContactsPresenter>(){

    private var contactsAdapter: ContactsAdapter? = null
    private var disposable : Disposable? = null

    override fun firstInit() {
        val mvpModel = ContactsModel()
        mvpPresenter = ContactsPresenter(mvpModel)
        mvpPresenter.attachView(this)
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
        activity.let {
            disposable = mvpPresenter.queryContactsAsync(it!!.contentResolver)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    setContacts(result)
                }, {throwable ->
                    showSnackbar(throwable.localizedMessage)
                })
        }
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