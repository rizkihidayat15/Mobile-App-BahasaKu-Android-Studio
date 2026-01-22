package com.example.bahasaku

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterNameActivity : AppCompatActivity() {

    private lateinit var backButton: ImageButton
    private lateinit var inputName: EditText
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_name)

        backButton = findViewById(R.id.button_back)
        inputName = findViewById(R.id.input_name)
        nextButton = findViewById(R.id.button_next)

        backButton.setOnClickListener {
            // Kembali ke halaman RegisterAge
            finish()
        }

        nextButton.setOnClickListener {
            val name = inputName.text.toString().trim()
            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, RegisterEmailActivity::class.java)
                intent.putExtra("user_name", name)
                startActivity(intent)
            }
        }

        // Fokus dan tampilkan keyboard otomatis saat layar muncul
        inputName.requestFocus()
    }
}