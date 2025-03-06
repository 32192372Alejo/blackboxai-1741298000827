package com.example.interviewface

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {

    private lateinit var nameEditText: TextInputEditText
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var registerButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize views
        nameEditText = findViewById(R.id.nameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        registerButton = findViewById(R.id.registerSubmitButton)

        // Set click listener for register button
        registerButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (validateInput(name, email, password)) {
                // TODO: Implement actual registration logic
                // For now, we'll just simulate a successful registration
                showToast("Registro exitoso")
                startActivity(Intent(this, InterviewActivity::class.java))
                finish()
            }
        }
    }

    private fun validateInput(name: String, email: String, password: String): Boolean {
        if (name.isEmpty()) {
            showToast("Por favor ingrese su nombre")
            return false
        }

        if (email.isEmpty()) {
            showToast("Por favor ingrese su correo electrónico")
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast("Por favor ingrese un correo electrónico válido")
            return false
        }

        if (password.isEmpty()) {
            showToast("Por favor ingrese su contraseña")
            return false
        }

        if (password.length < 6) {
            showToast("La contraseña debe tener al menos 6 caracteres")
            return false
        }

        return true
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
