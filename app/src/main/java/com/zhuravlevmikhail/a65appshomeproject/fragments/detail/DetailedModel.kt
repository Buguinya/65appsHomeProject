package com.zhuravlevmikhail.a65appshomeproject.fragments.detail

import android.net.Uri

const val FRAGMENT_DATA_KEY_CONTACT_ID = "FRAGMENT_DATA_KEY_CONTACT_ID"

data class ContactDetailed (
    val name : String,
    val phone: String,
    var email : String?,
    val image : Uri? = null
)
