package com.zhuravlevmikhail.a65appshomeproject.core.fragm_contact

import android.graphics.Bitmap

class ContactsModel : ContactsContract.TModel {

    data class ContactGeneral (
        val id : Long,
        val name : String,
        val phone : String
    )
}