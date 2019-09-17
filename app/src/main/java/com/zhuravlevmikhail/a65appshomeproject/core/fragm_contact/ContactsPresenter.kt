package com.zhuravlevmikhail.a65appshomeproject.core.fragm_contact

import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.BasePresenter

class ContactsPresenter(model: ContactsModel) :
    ContactsContract.TPresenter<ContactsView>,
    BasePresenter<ContactsView, ContactsModel>(model){
}