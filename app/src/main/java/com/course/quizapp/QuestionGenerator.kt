package com.course.quizapp

import kotlin.random.Random
import kotlin.random.nextInt


class QuestionGenerator {

    // PROPERTIES

    private var answerOptions = mutableListOf<Int>()

    private var correctAnswer: Int = 0
    private var answerTracker = mutableListOf<Int>()



    // MEHTODS

    private fun optionsRandomNumber() : Int {
        return Random.nextInt(Countries.indices)
    }

    private fun answerRandomNumber() : Int {
        return Random.nextInt(answerOptions.indices)
    }

    fun optionsGenerator() {
        var randomNum = optionsRandomNumber()
        clearOptions()

        while (answerOptions.size < 4) {
            if (!answerOptions.contains(randomNum)) {
                answerOptions.add(randomNum)
                randomNum = optionsRandomNumber()
            } else {
                randomNum = optionsRandomNumber()
            }
        }
        answerGenerator()
    }


    // generate answer
    fun answerGenerator() {
        var randomNum  = answerRandomNumber()
        while (answerTracker.contains(answerOptions[randomNum])) {
            randomNum = answerRandomNumber()
        }
        correctAnswer = answerOptions[randomNum]
        answerTracker.add(answerOptions[randomNum])
    }


    // clear option list
   private fun clearOptions() {
        answerOptions.clear()
    }

    // clear answer tracker
    fun clearAnswerTracker() {
        answerTracker.clear()

    }

    fun getAnswerTracker(): MutableList<Int> {
        return answerTracker
    }

    // retrieve options
    fun getOptions(): MutableList<Int> {
            return answerOptions
        }
    // retrieve answer
    fun getCorrectAnswer(): Int {
        return correctAnswer
    }



}
