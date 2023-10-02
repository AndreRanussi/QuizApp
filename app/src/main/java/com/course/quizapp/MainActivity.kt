package com.course.quizapp

import android.content.Intent
import android.media.session.MediaSession.QueueItem
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.WindowCompat


class MainActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //APP SETTINGS
        WindowCompat.setDecorFitsSystemWindows(
            window, false
        )

        //APP FUNCTIONALITY
        val btnStart: Button = findViewById(R.id.btnStart)
        val etName: EditText = findViewById(R.id.etName)
        var playerName: String = ""


        btnStart.setOnClickListener {
            val questionGenerator = QuestionGenerator()

            if(etName.text.isNotEmpty()) {
                playerName = etName.text.toString()
                questionGenerator.optionsGenerator()
                val intent = Intent(this, QuizQuestionsActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_LONG).show()
            }
        }

    }
}