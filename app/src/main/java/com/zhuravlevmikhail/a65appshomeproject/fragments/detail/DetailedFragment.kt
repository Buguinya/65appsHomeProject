package com.zhuravlevmikhail.a65appshomeproject.fragments.detail

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.*
import android.widget.Toast
import com.zhuravlevmikhail.a65appshomeproject.R
import com.zhuravlevmikhail.a65appshomeproject.appManagers.PermissionManager
import com.zhuravlevmikhail.a65appshomeproject.common.AppConst
import com.zhuravlevmikhail.a65appshomeproject.core.App
import kotlinx.android.synthetic.main.fragm_con_detailed.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import javax.inject.Provider

class DetailedFragment :
        DetailedView,
        MvpAppCompatFragment(){

    var contactId : Long = 0

    @Inject
    lateinit var presenterProvider : Provider<DetailedPresenter>

    @InjectPresenter
    lateinit var detailedPresenter: DetailedPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contactId = arguments?.get(FRAGMENT_DATA_KEY_CONTACT_ID) as Long
        setHasOptionsMenu(true)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.instance.appComponent.plusDetailedContactComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragm_con_detailed, container, false)
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == AppConst.PERMISSION_REQUEST_CODE_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                detailedPresenter.onContactsPermissionApproved(contactId)
            }
        }
    }

    override fun requestContactsPermission() {
        if (!PermissionManager.requestContactsPermission(this)) {
            detailedPresenter.onContactsPermissionApproved(contactId)
        }
    }

    override fun onReceivedContact(contact: ContactDetailed) {
        fillFields(contact)
    }

    override fun showError(error: Int) {
        val text = getString(error)
        getToastShort(text).show()
    }

    override fun showError(error: String) {
        getToastShort(error).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_contact_options, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_contact_location -> {
                detailedPresenter.onLocationClicked(contactId)
                return true}
            else -> {super.onOptionsItemSelected(item)}
        }
    }

    @ProvidePresenter
    fun getPresenter() : DetailedPresenter {
        return presenterProvider.get()
    }

    private fun getToastShort(message: String): Toast {
        return Toast.makeText(context, message, Toast.LENGTH_SHORT)
    }

    private fun fillFields(contact : ContactDetailed) {
        detContactName.text = contact.name
        detContactPhone.text = contact.phone
        detContactEmail.text = contact.email
        if (contact.image == null) {
            defContactImage.setImageToDefault()
        } else {
            defContactImage.setImageURI(contact.image)
        }
    }
}