package com.zhuravlevmikhail.a65appshomeproject.fragments.detail

import android.content.ContentResolver
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.zhuravlevmikhail.a65appshomeproject.R
import com.zhuravlevmikhail.a65appshomeproject.api.contentProvider.ContactsProvider
import kotlinx.android.synthetic.main.fragm_con_detailed.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class DetailedFragment :
        DetailedView,
        MvpAppCompatFragment(){

    private var contactId : Long = 0
    lateinit var contentResolver: ContentResolver

    @InjectPresenter
    lateinit var detailedPresenter: DetailedPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contactId = arguments?.get(FRAGMENT_DATA_KEY_CONTACT_ID) as Long
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        contentResolver = context.contentResolver
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragm_con_detailed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailedPresenter.queryContactAsync(contactId)
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

    @ProvidePresenter
    fun getPresenter() : DetailedPresenter {
        return DetailedPresenter(ContactsProvider(contentResolver))
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