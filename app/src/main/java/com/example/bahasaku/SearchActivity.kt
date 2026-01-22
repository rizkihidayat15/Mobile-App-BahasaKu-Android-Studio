package com.example.bahasaku

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class SearchActivity : AppCompatActivity() {

    // Data model untuk bahasa dan materi
    data class LanguageCourse(
        val name: String,
        val subtitle: String,
        val iconRes: Int,
        val activityClass: Class<*>
    )

    // Contoh data yang di-display
    private val languageCourses = listOf(
        LanguageCourse("Sundanese", "Introduction", R.drawable.ic_sundanese, SundaneseActivity::class.java),
        //LanguageCourse("Balinese", "Grammar", R.drawable.ic_balinese, BalineseActivity::class.java),
        //LanguageCourse("Aceh", "Pronunciation", R.drawable.ic_aceh, AcehActivity::class.java),
        //LanguageCourse("Javanese", "Vocabulary", R.drawable.ic_javanese, JavaneseActivity::class.java),
        //LanguageCourse("Betawi", "Basics", R.drawable.ic_betawi, BetawiActivity::class.java)
    )

    private lateinit var recyclerView: RecyclerView
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        //recyclerView = findViewById(R.id.recycler_previous_courses)
        bottomNav = findViewById(R.id.bottomNav)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = LanguageCourseAdapter(languageCourses) { course ->
            startActivity(Intent(this, course.activityClass))
        }

        bottomNav.selectedItemId = R.id.menu_search
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_learn -> {
                    startActivity(Intent(this, HomePageActivity::class.java))
                    true
                }
                R.id.menu_search -> {
                    startActivity(Intent(this, SearchActivity::class.java))
                    true
                }
                R.id.menu_achievement -> {
                    startActivity(Intent(this, AchievementActivity::class.java))
                    true
                }
                R.id.menu_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    // Adapter RecyclerView untuk menampilkan card bahasa dan materinya
    class LanguageCourseAdapter(
        private val courses: List<LanguageCourse>,
        private val onItemClick: (LanguageCourse) -> Unit
    ) : RecyclerView.Adapter<LanguageCourseAdapter.CourseViewHolder>() {

        inner class CourseViewHolder(containerView: ViewGroup) :
            RecyclerView.ViewHolder(
                LayoutInflater.from(containerView.context).inflate(R.layout.item_language_card_sundanese, containerView, false)
            ) {
            private val ivIcon: ImageView = itemView.findViewById(R.id.image_language_icon)
            private val tvName: TextView = itemView.findViewById(R.id.text_language_name)
            private val tvSubtitle: TextView = itemView.findViewById(R.id.text_language_subtitle)

            fun bind(course: LanguageCourse) {
                ivIcon.setImageResource(course.iconRes)
                tvName.text = course.name
                tvSubtitle.text = course.subtitle
                itemView.setOnClickListener {
                    onItemClick(course)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder =
            CourseViewHolder(parent)

        override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
            holder.bind(courses[position])
        }

        override fun getItemCount() = courses.size
    }
}