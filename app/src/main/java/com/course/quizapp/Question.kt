package com.course.quizapp

import android.media.Image

data class Question(
    val question: String = "What country does this flag belong to?",
    val image: String,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val correctAnswer: Int,
)