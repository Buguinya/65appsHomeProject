package com.zhuravlevmikhail.a65appshomeproject.core.detail

import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.BaseFragmAndView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragm_con_detailed.*

class DetailedView :
        DetailedContract.DetailedViewContract,
        BaseFragmAndView<DetailedModel, DetailedView, DetailedPresenter>(){

    private var contactId : Long = 0
    private var disposable : Disposable? = null


    override fun firstInit() {
        val mvpModel = DetailedModel()
        mvpPresenter = DetailedPresenter(model = mvpModel)
        mvpPresenter.attachView(view = this)
        contactId = arguments?.get(FRAGMENT_DATA_KEY_CONTACT_ID) as Long
    }

    override fun loadData() {
        configureObserver()
    }

    private fun fillFields(contact : DetailedModel.ContactDetailed) {
        detContactName.text = contact.name
        detContactPhone.text = contact.phone
        detContactEmail.text = contact.email
        if (contact.image == null) {
            defContactImage.setImageToDefault()
        } else {
            defContactImage.setImageURI(contact.image)
        }
    }

    private fun configureObserver() {
        activity?.let {
            disposable =
                mvpPresenter.queryContactAsync(it.contentResolver, contactId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ result ->
                        fillFields(result)
                    }, { throwable ->
                        showSnackbar(throwable.localizedMessage)
                    })
        }
    }
}