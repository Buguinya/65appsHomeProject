package com.zhuravlevmikhail.a65appshomeproject.core.fragm_con_detailed

import android.content.ContentResolver
import android.graphics.Bitmap
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.MvpModel
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.MvpPresenter
import com.zhuravlevmikhail.a65appshomeproject.core.mvpAchitecture.MvpView
import io.reactivex.Observable

interface DetailedContract {

    interface TModel : MvpModel

    interface TView : MvpView

    interface TPresenter<V : TView> : MvpPresenter<V> {
        fun queryContactWithoutImageAsync(contentResolver: ContentResolver, contactId: Long): Observable<DetailedModel.ContactDetailed>
        /*fun queryContactImageAsync(
            contentResolver: ContentResolver,
            contactId: Long
        ): Observable<Bitmap>*/
    }
}