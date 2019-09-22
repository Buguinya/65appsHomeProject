package com.zhuravlevmikhail.a65appshomeproject.core.detail

import android.graphics.Bitmap
import android.net.Uri

const val FRAGMENT_DATA_KEY_CONTACT_ID = "FRAGMENT_DATA_KEY_CONTACT_ID"
class DetailedModel : DetailedContract.DetailedModelContract {

    data class ContactDetailed (
        val name : String,
        val phone: String,
        var email : String,
        val image : Uri? = null
    )
}