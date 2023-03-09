package com.example.e_commerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock.sleep
import com.example.e_commerce.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // Initialize view binding object
        setContentView(binding.root)



        binding.hamburgericon.setOnClickListener {
        val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}