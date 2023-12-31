package com.course.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.WindowCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //APP SETTINGS
        WindowCompat.setDecorFitsSystemWindows(
            window, false
        )

        //APP FUNCTIONALITY
        val btnStart: Button = findViewById(R.id.btnStart)
        val etNumber: EditText = findViewById(R.id.etNumber)




        btnStart.setOnClickListener {
            val questionGenerator = QuestionGenerator()

            if(etNumber.text.isNotEmpty()) {
                questionGenerator.optionsGenerator()
                val intent =  Intent(this, QuizQuestionsActivity::class.java)
                val dataToPass = etNumber.text.toString().toInt()
                intent.putExtra("questions", dataToPass)

                startActivity(intent)
                finish()

            } else {
                Toast.makeText(this, "Please a Number of Questions", Toast.LENGTH_LONG).show()
            }
        }

    }


}