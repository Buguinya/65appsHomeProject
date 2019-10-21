package com.zhuravlevmikhail.a65appshomeproject.fragments.contacts

import android.app.SearchManager
import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.*
import android.view.View.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhuravlevmikhail.a65appshomeproject.R
import com.zhuravlevmikhail.a65appshomeproject.api.contentProvider.ContactsProvider
import com.zhuravlevmikhail.a65appshomeproject.appManagers.PermissionManager
import com.zhuravlevmikhail.a65appshomeproject.common.AppConst
import com.zhuravlevmikhail.a65appshomeproject.common.Utils
import com.zhuravlevmikhail.a65appshomeproject.common.interfaces.ContactsClickListener
import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.recycler.ContactsAdapter
import kotlinx.android.synthetic.main.fragm_contacts_list.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import kotlin.collections.ArrayList

class ContactsFragment :
    ContactsView,
    MvpAppCompatFragment(){

    lateinit var contentResolver: ContentResolver
    private var contactsAdapter: ContactsAdapter? = null

    @InjectPresenter
    lateinit var mvpPresenter : ContactsPresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        contentResolver = context.contentResolver
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragm_contacts_list, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search)?.actionView as (SearchView))
            .apply { setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))}
            .setOnQueryTextListener(object :SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?) = false
                override fun onQueryTextChange(newText: String?): Boolean {
                    this@ContactsFragment.checkContactsAccess()
                    if (Utils.isTrimmedNotEmpty(newText) && newText != null) {
                            mvpPresenter.onQueryChanged(newText)
                        } else {
                        mvpPresenter.onQueryDeleted()
                    }
                    return true
                }
            })
    }

    override fun onDestroy() {
        contactsAdapter = null
        super.onDestroy()
    }

    override fun checkContactsAccess() {
        if (!PermissionManager.requestContactsPermission(this)) {
            mvpPresenter.onContactsAccessGranted()
            textContactsNoPermission.visibility = GONE
        } else {
            textContactsNoPermission.visibility = VISIBLE
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == AppConst.PERMISSION_REQUEST_CODE_CONTACTS && grantResults.isNotEmpty()) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                this.checkContactsAccess()
                requireActivity().invalidateOptionsMenu()
                textContactsNoPermission.visibility = GONE
            }
        }
    }

    override fun onContactsReceived(contacts : ArrayList<ContactGeneral>) {
        this.configureContactsAdapter()
        setContacts(contacts)
    }

    override fun showError(error: Int) {
        val text = getString(error)
        getToastShort(text).show()
    }

    override fun showError(error: String) {
        getToastShort(error).show()
    }

    override fun showProgress(isLoading: Boolean) {
        progressCircular.visibility = if (isLoading) VISIBLE else GONE
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
        contactsAdapter?.fetchContacts(newContacts)
        if (newContacts.isEmpty()) {
            showError(getString(R.string.error_no_contacts))
        }
    }

    private fun getToastShort(message: String): Toast {
        return Toast.makeText(context, message, Toast.LENGTH_SHORT)
    }

    @ProvidePresenter
    fun getPresenter() : ContactsPresenter {
        return ContactsPresenter(ContactsProvider(contentResolver))
    }

    private val contactsClickListener = object : ContactsClickListener {
        override fun onClick(view: View, position: Int) {
            val id = contactsAdapter?.differ?.currentList?.get(position)?.id
            id?.let {
                mvpPresenter.onContactClicked(id)
            }
        }
    }
}