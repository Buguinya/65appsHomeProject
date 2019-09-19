package com.zhuravlevmikhail.a65appshomeproject.core.fragm_contact

import android.graphics.Bitmap

class ContactsModel : ContactsContract.TModel {

    data class ContactGeneral (
        val id : String,
        val name : String,
        val phone : String
    )

    data class ContactDetailed (
        val name : String,
        val phone: String,
        val email : String,
        val image : Bitmap
    )
}