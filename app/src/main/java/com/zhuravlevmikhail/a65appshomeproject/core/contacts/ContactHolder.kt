package com.zhuravlevmikhail.a65appshomeproject.core.contacts

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.zhuravlevmikhail.a65appshomeproject.common.interfaces.ContactsClickListener
import kotlinx.android.synthetic.main.cell_contact_general.view.*

class ContactHolder(itemView: View, private val clickListener : ContactsClickListener) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            clickListener.onClick(itemView, adapterPosition)
        }
    }

    fun bind(model: ContactsModel.ContactGeneral) {

        with(itemView) {
            contactName.text = model.name
            contactPhone.text = model.phone
        }
    }
}