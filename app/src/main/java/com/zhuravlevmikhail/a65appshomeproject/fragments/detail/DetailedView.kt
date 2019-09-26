package com.zhuravlevmikhail.a65appshomeproject.fragments.detail

import com.zhuravlevmikhail.a65appshomeproject.api.contentProvider.ContactsProvider
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.BaseFragmAndView
import kotlinx.android.synthetic.main.fragm_con_detailed.*

class DetailedView :
        DetailedContract.DetailedViewContract,
        BaseFragmAndView<DetailedView, DetailedPresenter>(){

    private var contactId : Long = 0

    override fun firstInit() {
        activity?.let {
            mvpPresenter = DetailedPresenter(ContactsProvider(it.contentResolver))
            mvpPresenter.attachView(view = this)
            contactId = arguments?.get(FRAGMENT_DATA_KEY_CONTACT_ID) as Long
        }
    }

    override fun loadData() {
        mvpPresenter.queryContactAsync(contactId)
    }

    override fun onReceivedContact(contact: ContactDetailed) {
        fillFields(contact)
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