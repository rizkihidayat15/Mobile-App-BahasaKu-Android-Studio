package com.example.bahasaku

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var buttonBack: TextView
    private lateinit var buttonNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        buttonBack = findViewById(R.id.button_back)
        buttonNext = findViewById(R.id.button_next)

        buttonBack.setOnClickListener {
            // Kembali ke halaman Starter
            finish() // atau jika ingin pasti eksplisit ke StarterActivity:
            // val intent = Intent(this, StarterActivity::class.java)
            // startActivity(intent)
            // finish()
        }

        buttonNext.setOnClickListener {
            // Lanjut ke halaman RegisterAge
            val intent = Intent(this, RegisterAgeActivity::class.java)
            startActivity(intent)
        }
    }
}