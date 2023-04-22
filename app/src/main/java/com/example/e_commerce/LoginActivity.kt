package com.example.e_commerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.e_commerce.databinding.ActivityLoginBinding
import com.example.e_commerce.DisplayDatabaseInformation
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private lateinit var binding: ActivityLoginBinding // Declare view binding object

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater) // Initialize view binding object
        setContentView(binding.root)



        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        // Set click listener for the login/signup button
        binding.LoginButton.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            if (email == "" || password == "") {
                Toast.makeText(this, "Missing Inputs", Toast.LENGTH_LONG).show()
            } else {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Set user session persistence to "LOCAL"
                            FirebaseAuth.getInstance().currentUser?.getIdToken(true)

                            // Login successful, navigate to desired activity
                            Toast.makeText(this, "Login Successful.", Toast.LENGTH_LONG).show()
                            val intent = Intent(this, OptionsDashboard::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            // Login failed, display error message
                            Toast.makeText(this, "Authentication failed.", Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }




        binding.btnToggleLoginSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)

        }

    }
}
