package com.example.bahasaku

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.text.method.TransformationMethod
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class LoginActivity : AppCompatActivity() {

    private lateinit var inputEmail: EditText
    private lateinit var inputPassword: EditText
    private lateinit var togglePasswordButton: ImageButton
    private lateinit var loginButton: Button
    private lateinit var registerText: TextView
    private lateinit var errorLoginText: TextView

    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        inputEmail = findViewById(R.id.input_email)
        inputPassword = findViewById(R.id.input_password)
        togglePasswordButton = findViewById(R.id.button_toggle_password)
        loginButton = findViewById(R.id.button_login)
        registerText = findViewById(R.id.text_register)
        errorLoginText = findViewById(R.id.text_error_login)

        // Mulai dengan tombol login disable
        loginButton.isEnabled = false
        errorLoginText.visibility = TextView.GONE

        togglePasswordButton.setOnClickListener {
            if (isPasswordVisible) {
                inputPassword.transformationMethod = PasswordTransformationMethod()
                togglePasswordButton.setImageResource(R.drawable.ic_eye_off)
                isPasswordVisible = false
            } else {
                inputPassword.transformationMethod = null
                togglePasswordButton.setImageResource(R.drawable.ic_eye_off)
                isPasswordVisible = true
            }
            inputPassword.setSelection(inputPassword.text.length)
        }

        // Monitor perubahan email dan password untuk validasi
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val email = inputEmail.text.toString().trim()
                val password = inputPassword.text.toString()
                val emailValid = isValidGmailAddress(email)
                val passwordValid = password.length >= 8

                loginButton.isEnabled = emailValid && passwordValid
                if (!emailValid && email.isNotEmpty()) {
                    errorLoginText.text = "Please enter a valid Gmail address."
                    errorLoginText.visibility = TextView.VISIBLE
                } else if (!passwordValid && password.isNotEmpty()) {
                    errorLoginText.text = "Password must be at least 8 characters."
                    errorLoginText.visibility = TextView.VISIBLE
                } else {
                    errorLoginText.visibility = TextView.GONE
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        }

        inputEmail.addTextChangedListener(textWatcher)
        inputPassword.addTextChangedListener(textWatcher)

        loginButton.setOnClickListener {
            val email = inputEmail.text.toString().trim()
            val password = inputPassword.text.toString()

            // Simulasi cek user registered / valid login
            if (mockCheckUser(email, password)) {
                errorLoginText.visibility = TextView.GONE
                // Lanjut ke halaman HomePage
                val intent = Intent(this, HomePageActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                errorLoginText.text = "Invalid email or password."
                errorLoginText.visibility = TextView.VISIBLE
            }
        }

        registerText.setOnClickListener {
            // Beralih ke halaman Register (misalnya StarterActivity)
            val intent = Intent(this, StarterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isValidGmailAddress(email: String): Boolean {
        val gmailPattern = Regex("^[A-Za-z0-9._%+-]+@gmail\\.com$")
        return gmailPattern.matches(email)
    }

    /**
     * Fungsi simulasi cek user terdaftar dan password cocok.
     * Dalam aplikasi produksi, ganti dengan autentikasi backend.
     */
    private fun mockCheckUser(email: String, password: String): Boolean {
        // Contoh dummy user
        val dummyEmail = "user123@gmail.com"
        val dummyPassword = "User1234@"

        return email == dummyEmail && password == dummyPassword
    }
}