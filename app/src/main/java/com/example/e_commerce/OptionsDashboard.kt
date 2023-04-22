package com.example.e_commerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.e_commerce.databinding.ActivityOptionsDashboardBinding

class OptionsDashboard : AppCompatActivity() {
    private lateinit var binding: ActivityOptionsDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptionsDashboardBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.admin.setOnClickListener {
            val intent = Intent(this,SimpleDashboard::class.java)
            startActivity(intent)
            Toast.makeText(applicationContext,"Welcome", Toast.LENGTH_LONG).show()
        }

        binding.user.setOnClickListener {
            val intent = Intent(this,AddProperty::class.java)
            startActivity(intent)
            Toast.makeText(applicationContext,"Welcome", Toast.LENGTH_LONG).show()
        }

    }
}