package com.example.bahasaku

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.text.method.TransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class RegisterPasswordActivity : AppCompatActivity() {

    private lateinit var backButton: ImageButton
    private lateinit var inputPassword: EditText
    private lateinit var togglePasswordButton: ImageButton
    private lateinit var passwordStrengthText: TextView
    private lateinit var startButton: Button

    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_password)

        backButton = findViewById(R.id.button_back)
        inputPassword = findViewById(R.id.input_password)
        togglePasswordButton = findViewById(R.id.button_toggle_password)
        passwordStrengthText = findViewById(R.id.text_password_strength)
        startButton = findViewById(R.id.button_start)

        backButton.setOnClickListener {
            // Kembali ke halaman Register Email
            finish()
        }

        togglePasswordButton.setOnClickListener {
            if (isPasswordVisible) {
                // Set password hidden
                inputPassword.transformationMethod = PasswordTransformationMethod()
                togglePasswordButton.setImageResource(R.drawable.ic_eye_off)
                isPasswordVisible = false
            } else {
                // Set password visible
                inputPassword.transformationMethod = null
                togglePasswordButton.setImageResource(R.drawable.ic_eye_off)
                isPasswordVisible = true
            }
            // Pindahkan cursor ke akhir teks setelah toggle visibility
            inputPassword.setSelection(inputPassword.text.length)
        }

        startButton.isEnabled = false // Disable tombol awalnya

        inputPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val password = s.toString()
                // Validasi panjang minimal 8 karakter
                if (password.length >= 8) {
                    startButton.isEnabled = true
                    val strength = evaluatePasswordStrength(password)
                    passwordStrengthText.text = "How strong your password : $strength"
                    passwordStrengthText.setTextColor(
                        ContextCompat.getColor(this@RegisterPasswordActivity, strengthColor(strength))
                    )
                } else {
                    startButton.isEnabled = false
                    passwordStrengthText.text = "Password must be at least 8 characters"
                    passwordStrengthText.setTextColor(ContextCompat.getColor(this@RegisterPasswordActivity, android.R.color.holo_red_dark))
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        startButton.setOnClickListener {
            val password = inputPassword.text.toString()
            if (password.length >= 8) {
                // Lanjut ke halaman login
                val intent = Intent(this, LoginActivity::class.java)
                // Bisa pass data jika perlu
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Password must be at least 8 characters", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun evaluatePasswordStrength(password: String): String {
        // Simple strength evaluation (bisa lebih kompleks)
        return when {
            password.length >= 12 -> "Super Strong"
            password.length >= 10 -> "Strong"
            password.length >= 8 -> "Medium"
            else -> "Weak"
        }
    }

    private fun strengthColor(strength: String): Int {
        return when (strength) {
            "Super Strong" -> android.R.color.holo_green_dark
            "Strong" -> android.R.color.holo_green_light
            "Medium" -> android.R.color.holo_orange_light
            else -> android.R.color.holo_red_dark
        }
    }
}