package com.zhuravlevmikhail.a65appshomeproject.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zhuravlevmikhail.a65appshomeproject.R
import com.zhuravlevmikhail.a65appshomeproject.appManagers.PageManager

class AppHostActivity : AppCompatActivity(R.layout.activity_host) {

    private lateinit var pageManager: PageManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pageManager = App.instance.pageManager
        if (savedInstanceState == null) {
            pageManager.onCreate(this)
            pageManager.setContactsPage()
        }
    }
}
