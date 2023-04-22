package com.example.e_commerce

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TenantAdapter(private val tenantList: MutableList<Tenant>) : RecyclerView.Adapter<TenantAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tenant_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tenantList[position])
    }

    override fun getItemCount(): Int {
        return tenantList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tenant: Tenant) {
            itemView.findViewById<TextView>(R.id.tv_name).text = tenant.name
            itemView.findViewById<TextView>(R.id.tv_mobile).text = tenant.mobile
            itemView.findViewById<TextView>(R.id.tv_email).text = tenant.email
            itemView.findViewById<TextView>(R.id.tv_dob).text = tenant.dob
        }
    }
}
