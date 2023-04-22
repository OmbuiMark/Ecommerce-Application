package com.example.e_commerce

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView

class ItemAdapter(context: Context, itemList: MutableList<String>) : ArrayAdapter<String>(context, 0, itemList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        }

        val itemName = getItem(position)
        val itemNameTextView = view?.findViewById<TextView>(R.id.item_name_textview)
        itemNameTextView?.text = itemName

        val deleteButton = view?.findViewById<Button>(R.id.delete_button)
        deleteButton?.setOnClickListener {
            remove(itemName)
            notifyDataSetChanged()
        }

        return view!!
    }
}
