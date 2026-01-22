package com.example.bahasaku

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class SundaneseActivity : AppCompatActivity() {

    data class Feature(
        val name: String,
        val duration: String,
        val progressCurrent: Int,
        val progressTotal: Int,
        val iconRes: Int,
        val bgLevelRes: Int,
        val progressColor: Int
    )

    private val features = listOf(
        Feature(
            "Introduction",
            "1 Hour 30 Minutes",
            9,
            50,
            R.drawable.ic_intro,
            R.drawable.bg_level_1,
            0xFF8BC34A.toInt() // Hijau
        ),
        Feature(
            "Grammar",
            "1 Hour 45 Minutes",
            5,
            40,
            R.drawable.ic_grammar,
            R.drawable.bg_level_2,
            0xFFFFB300.toInt() // Oranye
        ),
        Feature(
            "Vocabulary",
            "1 Hour 2 Minutes",
            2,
            50,
            R.drawable.ic_vocabulary,
            R.drawable.bg_level_3,
            0xFFF44336.toInt() // Merah
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_sundanese)

        val backButton = findViewById<ImageButton>(R.id.button_back)
        val featureContainer = findViewById<LinearLayout>(R.id.layout_features)

        backButton.setOnClickListener {
            val intent = Intent(this, HomePageActivity::class.java)
            startActivity(intent)
            finish()
        }

        val inflater = LayoutInflater.from(this)
        featureContainer.removeAllViews()

        for (feature in features) {
            val card = inflater.inflate(R.layout.item_feature_card, featureContainer, false) as CardView

            val iconBg = card.findViewById<LinearLayout>(R.id.icon_level_bg)
            val icon = card.findViewById<ImageView>(R.id.icon_level)
            val name = card.findViewById<TextView>(R.id.text_feature_name)
            val duration = card.findViewById<TextView>(R.id.text_duration)
            val progressBar = card.findViewById<ProgressBar>(R.id.progress_bar)
            val progressText = card.findViewById<TextView>(R.id.text_progress)

            iconBg.setBackgroundResource(feature.bgLevelRes)
            icon.setImageResource(feature.iconRes)

            name.text = feature.name
            duration.text = feature.duration

            progressBar.max = feature.progressTotal
            progressBar.progress = feature.progressCurrent
            progressBar.progressTintList = android.content.res.ColorStateList.valueOf(feature.progressColor)

            progressText.text = "${feature.progressCurrent}/${feature.progressTotal}"

            // Tambahkan aksi klik hanya untuk fitur "Introduction"
            if (feature.name == "Introduction") {
                card.setOnClickListener {
                    val intent = Intent(this, IntroductionTestActivity::class.java)
                    startActivity(intent)
                }
            }

            featureContainer.addView(card)
        }
    }
}