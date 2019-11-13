package com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.zhuravlevmikhail.a65appshomeproject.common.interfaces.ContactsClickListener
import com.zhuravlevmikhail.a65appshomeproject.domain.entities.contacts.ContactGeneral
import kotlinx.android.synthetic.main.cell_contact_general.view.*

class ContactHolder(itemView: View, private val clickListener : ContactsClickListener) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                clickListener.onClick(itemView, adapterPosition)
            }
        }
    }

    fun bind(model: ContactGeneral) {
        with(itemView) {
            contactName.text = model.name
            contactPhone.text = model.phone
        }
    }
}