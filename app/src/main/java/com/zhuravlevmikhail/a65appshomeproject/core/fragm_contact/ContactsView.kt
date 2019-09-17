package com.zhuravlevmikhail.a65appshomeproject.core.fragm_contact

import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.BaseFragmAndView

class ContactsView :
    ContactsContract.TView,
    BaseFragmAndView<ContactsModel, ContactsView, ContactsPresenter>(){

    override fun firstInit() {
        val mvpModel = ContactsModel()
        _mvpPresenter = ContactsPresenter(mvpModel)
        _mvpPresenter.attachView(this)
    }
}