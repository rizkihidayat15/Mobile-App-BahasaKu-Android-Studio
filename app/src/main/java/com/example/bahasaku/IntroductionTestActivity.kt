package com.example.bahasaku

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class IntroductionTestActivity : AppCompatActivity() {

    private lateinit var buttonBack: ImageButton

    private lateinit var popupCorrectAnswer: LinearLayout
    private lateinit var textAnswer: TextView
    private lateinit var buttonNextQuestion: Button

    private lateinit var popupWrongAnswer: LinearLayout
    private lateinit var textWrongAnswerCorrect: TextView
    private lateinit var buttonNextQuestionWrong: Button

    private lateinit var choiceButtons: List<Button>

    private val correctAnswer = "Pangambung"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction_test)

        buttonBack = findViewById(R.id.button_back)

        popupCorrectAnswer = findViewById(R.id.popup_correct_answer)
        textAnswer = findViewById(R.id.text_answer)
        buttonNextQuestion = findViewById(R.id.button_next_question)

        popupWrongAnswer = findViewById(R.id.popup_wrong_answer)
        textWrongAnswerCorrect = findViewById(R.id.text_wrong_answer_correct)
        buttonNextQuestionWrong = findViewById(R.id.button_next_question_wrong)

        choiceButtons = listOf(
            findViewById(R.id.button_choice1),
            findViewById(R.id.button_choice2),
            findViewById(R.id.button_choice3),
            findViewById(R.id.button_choice4)
        )

        buttonBack.setOnClickListener {
                finish()
            }

        choiceButtons.forEach { button ->
            button.setOnClickListener {
                checkAnswer(button)
            }
        }

        buttonNextQuestion.setOnClickListener {
            // Reset untuk pertanyaan berikutnya
            popupCorrectAnswer.visibility = View.GONE
            resetChoices()
            // Intent pindah ke IntroductionTest2Activity
            val intent = Intent(this, IntroductionTest2Activity::class.java)
            startActivity(intent)
            finish()
        }

        buttonNextQuestionWrong.setOnClickListener {
            // Reset untuk pertanyaan berikutnya
            popupWrongAnswer.visibility = View.GONE
            resetChoices()
            // Intent pindah ke IntroductionTest2Activity
            val intent = Intent(this, IntroductionTest2Activity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun checkAnswer(selectedButton: Button) {
        if (selectedButton.text == correctAnswer) {
            // Jawaban benar: ubah warna tombol jadi hijau
            selectedButton.setBackgroundColor(Color.parseColor("#B4D258"))  // hijau
            selectedButton.setTextColor(Color.WHITE)

            popupCorrectAnswer.visibility = View.VISIBLE
            popupWrongAnswer.visibility = View.GONE

            textAnswer.text = "Answer: $correctAnswer"

            disableChoiceButtons()
        } else {
            // Jawaban salah: ubah warna tombol jadi merah
            selectedButton.setBackgroundColor(Color.parseColor("#CC3344")) // merah
            selectedButton.setTextColor(Color.WHITE)
            selectedButton.isEnabled = false

            // Tampilkan popup jawaban salah
            popupWrongAnswer.visibility = View.VISIBLE
            popupCorrectAnswer.visibility = View.GONE
            textWrongAnswerCorrect.text = "Correct answer is: $correctAnswer"
        }
    }

    private fun disableChoiceButtons() {
        choiceButtons.forEach { it.isEnabled = false }
    }

    private fun resetChoices() {
        choiceButtons.forEach {
            it.isEnabled = true
            it.setBackgroundColor(Color.parseColor("#2296F3"))  // biru default
            it.setTextColor(Color.WHITE)
        }
    }
}