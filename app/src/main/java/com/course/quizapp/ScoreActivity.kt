package com.course.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)


        val resultScore: TextView = findViewById(R.id.resultScore)
        val outOf: TextView = findViewById(R.id.resultOutOf)
        val btnAgain: Button = findViewById(R.id.btnAgain)


        val correctAnsCount = intent.getIntExtra("correctAnsCount", 0)
        val totalQuestions = intent.getIntExtra("questions", 0)



        resultScore.text = correctAnsCount.toString()
        outOf.text = getString(R.string.totalQuestionsString, totalQuestions)

        btnAgain.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }




    }
}