package com.zhuravlevmikhail.a65appshomeproject.fragments.detail

import android.content.ContentResolver
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.MvpPresenter
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.MvpView
import io.reactivex.Single

interface DetailedContract {

    interface DetailedViewContract : MvpView

    interface DetailedPresenterContract<V : DetailedViewContract> : MvpPresenter<V> {
        fun queryContactAsync(contentResolver: ContentResolver, contactId: Long): Single<ContactDetailed>
    }
}