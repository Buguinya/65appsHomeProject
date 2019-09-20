package com.zhuravlevmikhail.a65appshomeproject.core.fragm_contact

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.zhuravlevmikhail.a65appshomeproject.common.interfaces.ContactsClickListener
import kotlinx.android.synthetic.main.cell_contact_general.view.*

class ContactHolder(itemView: View, private val _clickListener : ContactsClickListener) : RecyclerView.ViewHolder(itemView) {

    fun bind(model: ContactsModel.ContactGeneral) {
        itemView.setOnClickListener {
            _clickListener.onClick(itemView, model.id)
        }

        with(itemView) {
            contactName.text = model.name
            contactPhone.text = model.phone
        }
    }
}