package com.example.bahasaku

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterAgeActivity : AppCompatActivity() {

    private lateinit var backButton: ImageButton
    private lateinit var inputAge: EditText
    private lateinit var nextButton: Button
    private lateinit var skipButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_age)

        backButton = findViewById(R.id.button_back)
        inputAge = findViewById(R.id.input_age)
        nextButton = findViewById(R.id.button_next)
        skipButton = findViewById(R.id.button_skip)

        backButton.setOnClickListener {
            // Kembali ke halaman Register
            finish()
        }

        nextButton.setOnClickListener {
            val ageText = inputAge.text.toString().trim()
            if (ageText.isEmpty()) {
                Toast.makeText(this, "Please enter your age", Toast.LENGTH_SHORT).show()
            } else {
                // Bisa beri validasi umur tambahan jika perlu
                val intent = Intent(this, RegisterNameActivity::class.java)
                intent.putExtra("user_age", ageText)
                startActivity(intent)
            }
        }

        skipButton.setOnClickListener {
            // Langsung lanjut ke RegisterName tanpa input umur
            val intent = Intent(this, RegisterNameActivity::class.java)
            startActivity(intent)
        }

        // Otomatis fokus pada input umur dan menampilkan keyboard
        inputAge.requestFocus()
    }
}