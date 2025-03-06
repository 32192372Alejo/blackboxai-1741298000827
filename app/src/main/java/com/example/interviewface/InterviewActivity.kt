package com.example.interviewface

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class InterviewActivity : AppCompatActivity() {

    private lateinit var questionNumberTextView: TextView
    private lateinit var questionTextView: TextView
    private lateinit var answerEditText: TextInputEditText
    private lateinit var nextButton: MaterialButton
    private lateinit var timerTextView: TextView
    private lateinit var timer: CountDownTimer

    private var currentQuestionIndex = 0
    private val questions = listOf(
        "¿Cuál es tu mayor fortaleza profesional?",
        "¿Por qué te interesa trabajar en nuestra empresa?",
        "Describe una situación difícil en tu trabajo anterior y cómo la resolviste",
        "¿Dónde te ves profesionalmente en 5 años?",
        "¿Cuál ha sido tu mayor logro profesional?",
        "¿Cómo manejas el trabajo bajo presión?",
        "¿Por qué deberíamos contratarte?",
        "¿Cuál es tu experiencia trabajando en equipo?",
        "¿Cuál es tu mayor debilidad y cómo la manejas?",
        "¿Tienes alguna pregunta para nosotros?"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interview)

        initializeViews()
        setupClickListeners()
        updateQuestion()
        startTimer()
    }

    private fun initializeViews() {
        questionNumberTextView = findViewById(R.id.questionNumberTextView)
        questionTextView = findViewById(R.id.questionTextView)
        answerEditText = findViewById(R.id.answerEditText)
        nextButton = findViewById(R.id.nextButton)
        timerTextView = findViewById(R.id.timerTextView)
    }

    private fun setupClickListeners() {
        nextButton.setOnClickListener {
            if (answerEditText.text.toString().isEmpty()) {
                showToast("Por favor, proporciona una respuesta")
                return@setOnClickListener
            }

            saveAnswer()
            currentQuestionIndex++

            if (currentQuestionIndex < questions.size) {
                updateQuestion()
                startTimer()
            } else {
                finishInterview()
            }
        }
    }

    private fun updateQuestion() {
        questionNumberTextView.text = "Pregunta ${currentQuestionIndex + 1}/${questions.size}"
        questionTextView.text = questions[currentQuestionIndex]
        answerEditText.text?.clear()
    }

    private fun startTimer() {
        if (::timer.isInitialized) {
            timer.cancel()
        }

        timer = object : CountDownTimer(120000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / 1000 / 60
                val seconds = millisUntilFinished / 1000 % 60
                timerTextView.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                timerTextView.text = "00:00"
                showToast("¡Tiempo agotado!")
                if (currentQuestionIndex < questions.size - 1) {
                    currentQuestionIndex++
                    updateQuestion()
                    startTimer()
                } else {
                    finishInterview()
                }
            }
        }.start()
    }

    private fun saveAnswer() {
        // TODO: Implement answer saving logic
        // For now, we'll just show a toast
        showToast("Respuesta guardada")
    }

    private fun finishInterview() {
        showToast("¡Entrevista completada!")
        // TODO: Implement feedback or results screen
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::timer.isInitialized) {
            timer.cancel()
        }
    }
}
