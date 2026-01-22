package com.example.bahasaku  // Ganti dengan package aplikasi Anda

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private val splashDuration = 4000L // durasi splash screen 2 detik

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch {
            delay(splashDuration)
            startActivity(Intent(this@SplashActivity, StarterActivity::class.java))
            finish()
        }
    }
}