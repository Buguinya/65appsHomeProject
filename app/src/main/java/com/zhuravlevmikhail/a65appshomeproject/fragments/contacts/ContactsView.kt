package com.zhuravlevmikhail.a65appshomeproject.fragments.contacts

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ContactsView : MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class) fun checkContactsAccess()
    @StateStrategyType(AddToEndSingleStrategy::class)    fun onContactsReceived(contacts: ArrayList<ContactGeneral>)
    @StateStrategyType(OneExecutionStateStrategy::class) fun showError(error : String)
    @StateStrategyType(OneExecutionStateStrategy::class) fun showError(error: Int)
    @StateStrategyType(OneExecutionStateStrategy::class) fun showProgress(isLoading : Boolean)
}