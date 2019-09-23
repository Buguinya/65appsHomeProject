package com.zhuravlevmikhail.a65appshomeproject.core.detail

import android.content.ContentResolver
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.MvpModel
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.MvpPresenter
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.MvpView
import io.reactivex.Observable
import io.reactivex.Single

interface DetailedContract {

    interface DetailedModelContract : MvpModel

    interface DetailedViewContract : MvpView

    interface DetailedPresenterContract<V : DetailedViewContract> : MvpPresenter<V> {
        fun queryContactAsync(contentResolver: ContentResolver, contactId: Long): Single<DetailedModel.ContactDetailed>
    }
}