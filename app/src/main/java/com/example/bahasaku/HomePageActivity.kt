
package com.example.bahasaku

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePageActivity : AppCompatActivity() {

    // Card bahasa
    private lateinit var cardSundanese: View
    private lateinit var cardBalinese: View
    private lateinit var cardAceh: View
    private lateinit var cardJavanese: View
    private lateinit var cardBetawi: View

    // Bottom navigation
    private lateinit var bottomNav: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
        // Jika layout kamu bernama lain, sesuaikan di sini

        // Inisialisasi card bahasa
        cardSundanese = findViewById(R.id.cardSundanese)
        cardBalinese = findViewById(R.id.cardBalinese)
        cardAceh = findViewById(R.id.cardAceh)
        cardJavanese = findViewById(R.id.cardJavanese)
        cardBetawi = findViewById(R.id.cardBetawi)

        // Inisialisasi bottom navigation
        bottomNav = findViewById(R.id.bottomNav)

        // Klik kelas bahasa
        cardSundanese.setOnClickListener {
            val intent = Intent(this, SundaneseActivity::class.java)
            startActivity(intent)
        }

        // Set menu default aktif
        bottomNav.selectedItemId = R.id.menu_learn

        // Tambahkan listener untuk bottom navigation
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.menu_learn -> {
                    // Sudah di halaman HomePage
                    true
                }
                R.id.menu_search -> {
                    // Pindah ke SearchActivity
                    startActivity(Intent(this, SearchActivity::class.java))
                    true
                }
                R.id.menu_achievement -> {
                    // Pindah ke AchievementActivity saat menu Achievement diklik
                    startActivity(Intent(this, AchievementActivity::class.java))
                    true
                }
                R.id.menu_profile -> {
                    // Pindah ke ProfileActivity
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}