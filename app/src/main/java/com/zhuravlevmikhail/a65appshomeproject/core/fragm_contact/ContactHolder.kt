package com.zhuravlevmikhail.a65appshomeproject.core.fragm_contact

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cell_contact_general.view.*

class ContactHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(model: ContactsModel.ContactGeneral) {
        with(itemView) {
            contactName.text = model.name
            contactPhone.text = model.phone
        }
    }
}