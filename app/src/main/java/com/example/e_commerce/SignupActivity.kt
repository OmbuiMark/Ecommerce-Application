package com.example.e_commerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.e_commerce.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Set click listener for the signup button
        binding.btnSignup.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            // Validate email and password
            if (isValidEmail(email) && isValidPassword(password)) {
                // Signup user with email and password
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Signup successful, display success message
                            Toast.makeText(this, "Signup successful!", Toast.LENGTH_SHORT).show()
                        } else {
                            // Signup failed, display error message
                            Toast.makeText(this, "Signup failed. Please try again later.", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                // Email or password is invalid, display error message
                Toast.makeText(this, "Check email and password(8 chars,uppercase,lowercase,Special character).", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=!])(?=\\S+\$).{8,}\$"
        return PASSWORD_PATTERN.toRegex().matches(password)
    }
}
