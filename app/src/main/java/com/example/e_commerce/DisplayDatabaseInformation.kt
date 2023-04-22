package com.example.e_commerce

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.e_commerce.databinding.ActivityDisplayDatabaseInformationBinding
import com.example.e_commerce.ItemAdapter

class DisplayDatabaseInformation : AppCompatActivity() {

    private lateinit var binding: ActivityDisplayDatabaseInformationBinding
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayDatabaseInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemList = mutableListOf<String>()
        itemAdapter = ItemAdapter(this, itemList)
        binding.gridView.adapter = itemAdapter
        binding.NextPage.setOnClickListener {
            Toast.makeText(this, "You Can add other page here.", Toast.LENGTH_LONG).show()
        }

        binding.addButton.setOnClickListener {
            val itemName = binding.itemText.text.toString()
            if (itemName.isNotEmpty()) {
                itemAdapter.add(itemName)
                binding.itemText.setText("")
            }
        }

        binding.gridView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val itemName = itemAdapter.getItem(position)
            // Do something with the clicked item
        }

        binding.gridView.onItemLongClickListener = AdapterView.OnItemLongClickListener { parent, view, position, id ->
            itemAdapter.remove(itemAdapter.getItem(position))
            if (itemAdapter.isEmpty) {
                binding.gridView.visibility = View.GONE
                binding.emptyTextView.visibility = View.VISIBLE
            }
            true
        }
    }
}
