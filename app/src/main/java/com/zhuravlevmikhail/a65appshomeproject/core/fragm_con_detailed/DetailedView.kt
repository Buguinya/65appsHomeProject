package com.zhuravlevmikhail.a65appshomeproject.core.fragm_con_detailed

import android.graphics.Bitmap
import android.media.Image
import android.net.Uri
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.BaseFragmAndView
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragm_con_detailed.*

class DetailedView :
        DetailedContract.TView,
        BaseFragmAndView<DetailedModel, DetailedView, DetailedPresenter>(){

    private var _contactId : Long = 0
    private var _disposable : Disposable? = null
    private var _disposableImage : Disposable? = null


    override fun firstInit() {
        val mvpModel = DetailedModel()
        _mvpPresenter = DetailedPresenter(model = mvpModel)
        _mvpPresenter.attachView(view = this)
        _contactId = getFragmentData()?.get(FRAGMENT_DATA_KEY_CONTACT_ID) as Long
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
        _disposable = _mvpPresenter.queryContactWithoutImageAsync(activity!!.contentResolver, _contactId)
            .subscribe ({result ->
                fillFields(result)
            }, {
                showSnackbar(it.localizedMessage)
            })

       /* _disposableImage = _mvpPresenter.queryContactImageAsync(activity!!.contentResolver, _contactId)
            .subscribe( {result ->
                fillImage(result)
            }, {
                showSnackbar(it.localizedMessage)
            })*/
    }
}