package com.example.e_commerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class ElectronicsDashboard : AppCompatActivity() {

    private var cartCount = 0
    private lateinit var cartCountTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_electronics_dashboard)

        cartCountTextView = findViewById(R.id.cart_count)
    }

    fun addToCart(view: View) {
        // Increment cart count and update TextView
        cartCount++
        cartCountTextView.text = cartCount.toString()
    }
}
