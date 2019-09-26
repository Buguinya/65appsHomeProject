package com.zhuravlevmikhail.a65appshomeproject.common.models

import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes


data class CustomAnimModel(@AnimatorRes @AnimRes val enter: Int,
                           @AnimatorRes @AnimRes val exit: Int,
                           @AnimatorRes @AnimRes val popEnter: Int,
                           @AnimatorRes @AnimRes val popExit: Int)