package com.example.bahasaku

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RegisterEmailActivity : AppCompatActivity() {

    private lateinit var backButton: ImageButton
    private lateinit var inputEmail: EditText
    private lateinit var nextButton: Button
    private lateinit var errorText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_email)

        backButton = findViewById(R.id.button_back)
        inputEmail = findViewById(R.id.input_email)
        nextButton = findViewById(R.id.button_next)
        errorText = findViewById(R.id.text_error_email)

        backButton.setOnClickListener {
            // Kembali ke halaman RegisterName
            finish()
        }

        // Disable tombol Next di awal
        nextButton.isEnabled = false

        // TextWatcher untuk memonitor input email dan validasi
        inputEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val emailInput = s?.toString()?.trim() ?: ""

                if (isValidGmailAddress(emailInput)) {
                    errorText.visibility = TextView.GONE
                    nextButton.isEnabled = true
                } else {
                    errorText.visibility = TextView.VISIBLE
                    nextButton.isEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        nextButton.setOnClickListener {
            val email = inputEmail.text.toString().trim()
            if (isValidGmailAddress(email)) {
                val intent = Intent(this, RegisterPasswordActivity::class.java)
                intent.putExtra("user_email", email)
                startActivity(intent)
            } else {
                // Tidak harus terjadi karena tombol disable ketika invalid,
                // tapi bisa berikan safe check.
                errorText.visibility = TextView.VISIBLE
            }
        }
    }

    /**
     * Validasi email dengan memastikan format email valid dan domain berakhiran @gmail.com
     */
    private fun isValidGmailAddress(email: String): Boolean {
        val gmailPattern = Regex("^[A-Za-z0-9._%+-]+@gmail\\.com$")
        return gmailPattern.matches(email)
    }
}