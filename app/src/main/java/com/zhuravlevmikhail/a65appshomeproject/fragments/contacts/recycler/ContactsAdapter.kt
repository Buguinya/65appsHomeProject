package com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zhuravlevmikhail.a65appshomeproject.R
import com.zhuravlevmikhail.a65appshomeproject.common.interfaces.ContactsClickListener
import com.zhuravlevmikhail.a65appshomeproject.domain.entities.contacts.ContactGeneral
import java.util.*

class ContactsAdapter(private val itemClickListener : ContactsClickListener) : RecyclerView.Adapter<ContactHolder>() {

    var contacts  = Collections.emptyList<ContactGeneral>()
    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val contactCell = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_contact_general, parent, false)
        return ContactHolder(
            contactCell,
            itemClickListener
        )
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    fun fetchContacts(contacts : List<ContactGeneral>) {
        differ.submitList(contacts)
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<ContactGeneral>() {
            override fun areItemsTheSame(oldItem: ContactGeneral, newItem: ContactGeneral) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: ContactGeneral,
                newItem: ContactGeneral
            ): Boolean {
                return oldItem.name.equals(newItem.name)
                        && oldItem.phone.equals(newItem.phone)
            }
        }
    }
}