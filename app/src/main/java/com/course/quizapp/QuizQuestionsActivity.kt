package com.course.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide

class QuizQuestionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)



        val buttonOne: Button = findViewById(R.id.buttonOne)
        val buttonTwo: Button = findViewById(R.id.buttonTwo)
        val buttonThree: Button = findViewById(R.id.buttonThree)
        val buttonFour: Button = findViewById(R.id.buttonFour)
        val flagImage: ImageView = findViewById(R.id.flagImage)

        // call options and answer method (initializing)
        var generate = QuestionGenerator()
        generate.optionsGenerator()
        generate.answerGenerator()

        // getting options and distracting them
        var options = generate.getOptions()
        var (numOne, numTwo, numThree, numFour) = options

        // destructuring each option to obtain the country code and name.
        var (oneCode, oneName) = Countries[numOne]
        var (twoCode, twoName) = Countries[numTwo]
        var (threeCode, threeName) = Countries[numThree]
        var (fourCode, fourName) = Countries[numFour]

        // getting answer
        var answer = generate.getCorrectAnswer()
        var (answersCode, answerName) = Countries[answer]


        Log.d("myTag", "$oneName")
        Log.d("myTag", "$twoName")
        Log.d("myTag", "$threeName")
        Log.d("myTag", "$fourName")
        Log.d("myTag", "$answerName")


        // dynamic image url for answer
        val url: String = "https://flagcdn.com/h240/$answersCode.jpg"

        buttonOne.text = oneName
        buttonTwo.text = twoName
        buttonThree.text = threeName
        buttonFour.text = fourName


        Glide
            .with(this)
            .load(url)
            .centerCrop()
            .into(flagImage);

        buttonOne.setOnClickListener {

        }


    }
}
