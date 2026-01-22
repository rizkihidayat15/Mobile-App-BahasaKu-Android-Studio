package com.example.bahasaku

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class IntroductionTest2Activity : AppCompatActivity() {

    private lateinit var buttonBack: ImageButton
    private lateinit var editTextAnswer: EditText

    private lateinit var popupCorrectAnswer: LinearLayout
    private lateinit var textCorrectAnswer: TextView
    private lateinit var buttonNextQuestion: Button

    private lateinit var popupWrongAnswer: LinearLayout
    private lateinit var textWrongAnswer: TextView
    private lateinit var buttonNextQuestionWrong: Button

    private lateinit var buttonCheckAnswer: Button

    private val correctAnswer = "Excuse me, my name is asep"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction_test2)

        buttonBack = findViewById(R.id.button_back)
        editTextAnswer = findViewById(R.id.editText_answer)

        popupCorrectAnswer = findViewById(R.id.popup_correct_answer)
        textCorrectAnswer = findViewById(R.id.text_correct_answer)
        buttonNextQuestion = findViewById(R.id.button_next_question)

        popupWrongAnswer = findViewById(R.id.popup_wrong_answer)
        textWrongAnswer = findViewById(R.id.text_wrong_answer)
        buttonNextQuestionWrong = findViewById(R.id.button_next_question_wrong)

        buttonCheckAnswer = findViewById(R.id.button_check_answer)

        // Tombol back untuk kembali ke halaman sebelumnya
        buttonBack.setOnClickListener {
            val intent = Intent(this, IntroductionTestActivity::class.java)
                startActivity(intent)
                finish()
            }

        buttonCheckAnswer.setOnClickListener {
            val userAnswer = editTextAnswer.text.toString().trim()
            if (userAnswer.equals(correctAnswer, ignoreCase = true)) {
                showCorrectAnswer()
            } else {
                showWrongAnswer()
            }
        }

        buttonNextQuestion.setOnClickListener {
            resetUIForNextQuestion()
        }

        buttonNextQuestionWrong.setOnClickListener {
            resetUIForNextQuestion()
        }
    }

    private fun showCorrectAnswer() {
        popupCorrectAnswer.visibility = View.VISIBLE
        popupWrongAnswer.visibility = View.GONE

        editTextAnswer.background = getDrawable(R.drawable.edit_text_green)
        textCorrectAnswer.text = "Answer: $correctAnswer"

        buttonCheckAnswer.text = "Next Question"
        buttonCheckAnswer.setBackgroundColor(Color.parseColor("#4CAF50")) // Hijau
    }

    private fun showWrongAnswer() {
        popupWrongAnswer.visibility = View.VISIBLE
        popupCorrectAnswer.visibility = View.GONE

        editTextAnswer.background = getDrawable(R.drawable.edit_text_red)
        textWrongAnswer.text = "Correct answer is: $correctAnswer"

        buttonCheckAnswer.text = "Next Question"
        //buttonCheckAnswer.setBackgroundColor(Color.parseColor("#F44336")) // Merah
    }

    private fun resetUIForNextQuestion() {
        popupCorrectAnswer.visibility = View.GONE
        popupWrongAnswer.visibility = View.GONE

        editTextAnswer.background = getDrawable(R.drawable.edit_text_default)
        editTextAnswer.text.clear()

        buttonCheckAnswer.text = "Check Answer"
        buttonCheckAnswer.setBackgroundColor(Color.parseColor("#2196F3")) // Biru
    }
}