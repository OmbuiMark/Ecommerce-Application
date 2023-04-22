package com.example.e_commerce

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce.databinding.ActivityAddPropertyBinding

class AddProperty : AppCompatActivity() {
    private lateinit var binding: ActivityAddPropertyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPropertyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val propertyList = mutableListOf<Property>()

        val recyclerView = findViewById<RecyclerView>(R.id.property_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PropertyAdapter(propertyList)

        binding.fabAddProperty.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Add Property")

            val layout = LinearLayout(this)
            layout.orientation = LinearLayout.VERTICAL
            layout.setPadding(50, 20, 50, 10)

            val nameInput = EditText(this)
            nameInput.hint = "Name"
            layout.addView(nameInput)

            val addressInput = EditText(this)
            addressInput.hint = "Address"
            layout.addView(addressInput)

            val sizeInput = EditText(this)
            sizeInput.hint = "Size (in sq. ft.)"
            sizeInput.inputType = InputType.TYPE_CLASS_NUMBER
            layout.addView(sizeInput)

            val rentInput = EditText(this)
            rentInput.hint = "Rent (per month)"
            rentInput.inputType = InputType.TYPE_CLASS_NUMBER
            layout.addView(rentInput)

            builder.setView(layout)

            builder.setPositiveButton("Add") { dialog, which ->
                val name = nameInput.text.toString()
                val address = addressInput.text.toString()
                val size = sizeInput.text.toString().toIntOrNull()
                val rent = rentInput.text.toString().toIntOrNull()

                if (name.isNotBlank() && address.isNotBlank() && size != null && rent != null) {
                    propertyList.add(Property(name, address, size, rent))
                    recyclerView.adapter?.notifyDataSetChanged()
                } else {
                    Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
                }
            }

            builder.setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()
        }
    }

}

data class Property(
    val name: String,
    val address: String,
    val size: Int,
    val rent: Int
)