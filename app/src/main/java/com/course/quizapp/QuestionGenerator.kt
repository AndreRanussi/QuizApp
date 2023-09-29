package com.course.quizapp

import kotlin.random.Random
import kotlin.random.nextInt


class QuestionGenerator {

    // answer options
    var options = mutableListOf<Int>()

    var correctAnswer: Int = 0
    var answerTracker = mutableListOf<Int>()


    // random number generator
    private fun generateRandomNumber() : Int {
        return Random.nextInt(0..249)
    }

// populate the list of option with 4 unique options
    fun optionsGenerator() {
        var randomNum = generateRandomNumber()
        clearOptions()
        // while look of 4 to check if random number is already in the options list, if not, add to the list, if yes regenerate a random number and retry.
        while (options.size < 5) {
            if (!options.contains(randomNum)) {
                options.add(randomNum)
                randomNum = generateRandomNumber()
            } else {
                randomNum = generateRandomNumber()
            }
        }
    }

    // clear option list
    fun clearOptions() {
        options.clear()
    }

    // generate answer



}
