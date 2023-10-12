package com.course.quizapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide

class QuizQuestionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)


// Buttons and Image fields retrieve
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val questionCounter: TextView = findViewById(R.id.questionCounter)
        val buttonOne: Button = findViewById(R.id.buttonOne)
        val buttonTwo: Button = findViewById(R.id.buttonTwo)
        val buttonThree: Button = findViewById(R.id.buttonThree)
        val buttonFour: Button = findViewById(R.id.buttonFour)
        val flagImage: ImageView = findViewById(R.id.flagImage)
        val generate = QuestionGenerator()

        var correctAnswer = ""
        var questionCount = 1
        val totalQuestions = intent.getIntExtra("questions", 0)
        var correctAnsCount = 0


        val right = "#007F2D"
        val wrong = "#E30613"

        fun start() {
            // show question counter
            generate.clearAnswerTracker()
            questionCounter.text = "$questionCount / $totalQuestions"
            //initialise the question generator
            generate.optionsGenerator()
            generate.answerGenerator()
            generate.getAnswerTracker()

            // getting options and destructing them
            val options = generate.getOptions()
            val (numOne, numTwo, numThree, numFour) = options

            // destructuring each option to obtain the country code and name.
            val (_, oneName) = Countries[numOne]
            val (_, twoName) = Countries[numTwo]
            val (_, threeName) = Countries[numThree]
            val (_, fourName) = Countries[numFour]

            // getting answer
            val answer = generate.getCorrectAnswer()
            val (answersCode, answerName) = Countries[answer]
            correctAnswer = answerName

            // Assign the options name to the buttons
            buttonOne.text = oneName
            buttonTwo.text = twoName
            buttonThree.text = threeName
            buttonFour.text = fourName

            // show the correct answer's flag
            val url = "https://flagcdn.com/h240/$answersCode.jpg"

            Glide
                .with(this)
                .load(url)
                .into(flagImage)


            val progressCount = 100/totalQuestions
            progressBar.incrementProgressBy(progressCount)
        }
        // call the start function to have a the initial(first) flag question.
        start()

        // disable buttons once a user makes a choice
        fun disableButtons() {
            buttonOne.isEnabled = false
            buttonTwo.isEnabled = false
            buttonThree.isEnabled = false
            buttonFour.isEnabled = false

        }

        // enables buttons when next round starts
        fun enableButtons() {
            buttonOne.isEnabled = true
            buttonTwo.isEnabled = true
            buttonThree.isEnabled = true
            buttonFour.isEnabled = true

        }

        // rest buttons colors for a new round
        fun resetColors() {
           buttonOne.setBackgroundColor(Color.WHITE)
            buttonTwo.setBackgroundColor(Color.WHITE)
            buttonThree.setBackgroundColor(Color.WHITE)
            buttonFour.setBackgroundColor(Color.WHITE)
            enableButtons()
        }

        // check and turn green the correct country names
        fun checkButton() {
            when (correctAnswer) {
                buttonOne.text -> buttonOne.setBackgroundColor(Color.parseColor(right))
                buttonTwo.text -> buttonTwo.setBackgroundColor(Color.parseColor(right))
                buttonThree.text -> buttonThree.setBackgroundColor(Color.parseColor(right))
                buttonFour.text -> buttonFour.setBackgroundColor(Color.parseColor(right))

            }
        }

        // check answer track score
        fun check(view: View) {
            val button = view as Button
            val buttonText = button.text

            if (buttonText == correctAnswer) {
                view.setBackgroundColor(Color.parseColor(right))
                correctAnsCount++
                questionCount++
            } else {
                view.setBackgroundColor(Color.parseColor(wrong))
                questionCount++
            }

            checkButton()
            val mainHandler = Handler(Looper.getMainLooper())
            disableButtons()
            mainHandler.postDelayed({

                // check score to know when to finish the game
                if(questionCount > totalQuestions) {

                    val intent = Intent(this, ScoreActivity::class.java)


                    intent.putExtra("questions", totalQuestions)
                    intent.putExtra("correctAnsCount", correctAnsCount)
                    startActivity(intent)
                    finish()
                    resetColors()

                } else {
                    start()
                    resetColors()
                }
                                    }, 1000)


        }



        // button clicks
        buttonOne.setOnClickListener {
            check(it)
        }

        buttonTwo.setOnClickListener {
            check(it)
        }

        buttonThree.setOnClickListener {
            check(it)
        }

        buttonFour.setOnClickListener {
            check(it)
        }

    }
}
