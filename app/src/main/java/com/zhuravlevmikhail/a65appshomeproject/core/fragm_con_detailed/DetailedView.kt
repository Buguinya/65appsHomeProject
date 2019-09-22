package com.zhuravlevmikhail.a65appshomeproject.core.fragm_con_detailed

import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.BaseFragmAndView
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragm_con_detailed.*

class DetailedView :
        DetailedContract.TView,
        BaseFragmAndView<DetailedModel, DetailedView, DetailedPresenter>(){

    private var contactId : Long = 0
    private var disposable : Disposable? = null


    override fun firstInit() {
        val mvpModel = DetailedModel()
        _mvpPresenter = DetailedPresenter(model = mvpModel)
        _mvpPresenter.attachView(view = this)
        contactId = getFragmentData()?.get(FRAGMENT_DATA_KEY_CONTACT_ID) as Long
    }

    override fun loadData() {
        configureObserver()
    }

    private fun fillFields(contact : DetailedModel.ContactDetailed) {
        detContactName.text = contact.name
        detContactPhone.text = contact.phone
        detContactEmail.text = contact.email
        defContactImage.assignContactFromPhone(contact.phone, true)
        defContactImage.assignContactFromEmail(contact.email, true)
    }

    private fun configureObserver() {
        disposable = _mvpPresenter.queryContactWithoutImageAsync(activity!!.contentResolver, contactId)
            .subscribe ({result ->
                fillFields(result)
            }, {
                showSnackbar(it.localizedMessage)
            })
    }
}