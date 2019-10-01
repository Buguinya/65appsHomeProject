package com.zhuravlevmikhail.a65appshomeproject.fragments.contacts

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ContactsView : MvpView {
    fun checkContactsAccess()
    fun onContactsReceived(contacts: ArrayList<ContactGeneral>)
    fun showError(error : String)
    fun showError(error: Int)
}