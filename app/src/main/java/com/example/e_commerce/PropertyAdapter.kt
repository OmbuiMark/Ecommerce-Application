package com.example.e_commerce

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PropertyAdapter(private val propertyList: List<Property>) :
    RecyclerView.Adapter<PropertyAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val propertyNameView: TextView = view.findViewById(R.id.property_name)
        val propertyAddressView: TextView = view.findViewById(R.id.property_address)
        val propertySizeView: TextView = view.findViewById(R.id.property_size)
        val propertyRentView: TextView = view.findViewById(R.id.property_rent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.property_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.propertyNameView.text = propertyList[position].name
        holder.propertyAddressView.text = propertyList[position].address
        holder.propertySizeView.text = "Size: ${propertyList[position].size} sq. ft."
        holder.propertyRentView.text = "Rent: $${propertyList[position].rent} per month"
    }

    override fun getItemCount() = propertyList.size
}
