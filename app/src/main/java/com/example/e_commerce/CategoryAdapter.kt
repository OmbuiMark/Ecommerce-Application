package com.example.e_commerce

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
class CategoryAdapter(private val items: List<DrawerMenu.CategoryItem>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.categoryImage.setImageResource(item.image)
        holder.categoryName.text = item.title

        // Add a click listener for the first item
        if (position == 0) {
            holder.itemView.setOnClickListener {
                // Start ElectronicsDashboardActivity
                val intent = Intent(holder.itemView.context, ElectronicsDashboard::class.java)
                holder.itemView.context.startActivity(intent)
            }
        }
    }


    override fun getItemCount() = items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryImage: ImageView = itemView.findViewById(R.id.category_image)
        val categoryName: TextView = itemView.findViewById(R.id.category_name)
    }
}
