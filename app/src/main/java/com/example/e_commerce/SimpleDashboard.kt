package com.example.e_commerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce.databinding.ActivitySimpleDashboardBinding

class SimpleDashboard : AppCompatActivity() {
    private lateinit var binding: ActivitySimpleDashboardBinding // Declare view binding object
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimpleDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tenantList = mutableListOf<Tenant>()

        val recyclerView = findViewById<RecyclerView>(R.id.tenant_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TenantAdapter(tenantList)

        binding.fabAddTenant.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Add Tenant")

            val layout = LinearLayout(this)
            layout.orientation = LinearLayout.VERTICAL
            layout.setPadding(50, 20, 50, 10)

            val nameInput = EditText(this)
            nameInput.hint = "Name"
            layout.addView(nameInput)

            val mobileInput = EditText(this)
            mobileInput.hint = "Mobile Number"
            mobileInput.inputType = InputType.TYPE_CLASS_PHONE
            layout.addView(mobileInput)

            val emailInput = EditText(this)
            emailInput.hint = "Email"
            emailInput.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            layout.addView(emailInput)

            val dobInput = EditText(this)
            dobInput.hint = "Date of Boarding"
            dobInput.inputType = InputType.TYPE_CLASS_DATETIME or InputType.TYPE_DATETIME_VARIATION_DATE
            layout.addView(dobInput)

            builder.setView(layout)

            builder.setPositiveButton("Add") { dialog, which ->
                val name = nameInput.text.toString()
                val mobile = mobileInput.text.toString()
                val email = emailInput.text.toString()
                val dob = dobInput.text.toString()

                if (name.isNotBlank() && mobile.isNotBlank() && email.isNotBlank() && dob.isNotBlank()) {
                    tenantList.add(Tenant(name, mobile, email, dob))
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

data class Tenant(
    val name: String,
    val mobile: String,
    val email: String,
    val dob: String
)
