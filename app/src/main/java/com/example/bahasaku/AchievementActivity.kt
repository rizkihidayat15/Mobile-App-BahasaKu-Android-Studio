package com.example.bahasaku

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.progressindicator.CircularProgressIndicator
import android.content.Intent

class AchievementActivity : AppCompatActivity() {

    data class Achievement(
        val title: String,
        val description: String,
        val iconRes: Int,
        val backgroundColor: Int,
        val starCount: Int // jumlah bintang yang terisi
    )

    private val achievements = listOf(
        Achievement(
            "Studious",
            "You have completed this lesson 10 times.",
            R.drawable.ic_trophy,
            Color.parseColor("#90CAF9"), // biru muda
            3
        ),
        Achievement(
            "Quickie",
            "You have completed this quiz in less than 3 minutes, 10 times.",
            R.drawable.ic_clock,
            Color.parseColor("#FFEB3B"), // kuning
            3
        ),
        Achievement(
            "Ambitious",
            "You have achieved 15 milestones.",
            R.drawable.ic_medal,
            Color.parseColor("#1BA798"), // hijau toska
            3
        ),
        Achievement(
            "Perfectionist",
            "You have scored 100% on quizzes 20 times.",
            R.drawable.ic_perfect,
            Color.parseColor("#3B7DFF"), // biru
            3
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_achievement)

        val circularProgress = findViewById<CircularProgressIndicator>(R.id.circular_progress)
        val textTotalAchievements = findViewById<TextView>(R.id.text_total_achievements)
        val textAchievementSub = findViewById<TextView>(R.id.text_achievement_sub)
        val achievementContainer = findViewById<LinearLayout>(R.id.achievement_cards_container)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        // Set progress circular
        val totalProgressPercent = 80
        circularProgress.progress = totalProgressPercent
        circularProgress.trackColor = Color.parseColor("#D6D6D6")
        circularProgress.setIndicatorColor(Color.parseColor("#4CAF50")) // hijau

        // Set text total achievements
        textTotalAchievements.text = "Total Achievements : 20"
        textAchievementSub.text = "Great job, John! Complete your achievements now"

        // Tambah card achievement secara dinamis
        val inflater = LayoutInflater.from(this)
        achievementContainer.removeAllViews()

        achievements.forEach { achievement ->
            val cardView = inflater.inflate(R.layout.item_achievement_card_studious, achievementContainer, false) as CardView

            val cardBackground = cardView.findViewById<LinearLayout>(R.id.card_background)
            val icon = cardView.findViewById<ImageView>(R.id.achievement_icon)
            val title = cardView.findViewById<TextView>(R.id.achievement_title)
            val description = cardView.findViewById<TextView>(R.id.achievement_description)

            // Ganti background card sesuai warna
            cardBackground.setBackgroundColor(achievement.backgroundColor)
            icon.setImageResource(achievement.iconRes)
            title.text = achievement.title
            description.text = achievement.description

            // Set bintang star filled dan outline
            for (i in 1..5) {
                val starId = resources.getIdentifier("star$i", "id", packageName)
                val starView = cardView.findViewById<ImageView>(starId)
                if (i <= achievement.starCount) {
                    starView.setImageResource(R.drawable.ic_star_filled)
                } else {
                    starView.setImageResource(R.drawable.ic_star_outline)
                }
            }

            achievementContainer.addView(cardView)
        }

        // Setup bottom navigation bar menu aktif di achievement
        bottomNav.selectedItemId = R.id.menu_achievement
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.menu_learn -> {
                    startActivity(Intent(this, HomePageActivity::class.java))
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    true
                }
                R.id.menu_search -> {
                    startActivity(Intent(this, SearchActivity::class.java))
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    true
                }
                R.id.menu_achievement -> true

                R.id.menu_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    true
                }
                else -> false
            }
        }
    }
}