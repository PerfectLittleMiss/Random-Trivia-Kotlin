package com.laukinaole.kotlinquizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game.*
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    var allQuestions = mapOf("The book series 'Skulduggery Pleasant' was written by: \n\n A) Derek Landy\n\n B) Dave Barry\n\n C) Nancy Springer" to 1,
        "The word 'hi' in French is:\n\n A) Bonjour\n\n B) Salut\n\n C) Hola" to 2,
        "Which of the following is a French singer?\n\n A) Tamora Pierce\n\n B) Gaston Leroux\n\n C) Indila" to 3,
        "Which day of the week is DaParty day?\n\n A) Wednesday\n\n B) Friday\n\n C) Sunday" to 1,
        "Which episode of Sherlock is the phrase 'Honey you should see me in a crown' from?\n\n A) A Scandal in Belgravia\n\n B) The Great Game\n\n C) The Reichenbach Fall" to 3,
        "Who is the most recent winner of the King of the Ring tournament?\n\n A) Booker T\n\n B) Baron Corbin\n\n C) Roman Reigns" to 2,
        "Which city is not in Europe?\n\n A) Toulouse\n\n B) Montreal\n\n C) Montpellier" to 2,
        "Which letter does not appear 8 times when listing the days of the week?\n\n A) 'd'\n\n B) a\n\n C) y" to  3,
        "Which creature appears most often in different cultures?\n\n A) Dragon\n\n B) Sphinx\n\n C) Dinosaur" to 1,
        "Who's also known as the Spanish God?\n\n A) Angel Garza\n\n B) Santos Escobar\n\n C) Sammy Guevara" to 3)

    var correctAnswers = 0;
    var remainingQuestions = mutableMapOf<String, Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        buttonA.setOnClickListener {
            showToast(1)
        }
        buttonB.setOnClickListener {
            showToast(2)
        }
        buttonC.setOnClickListener {
            showToast(3)
        }
        buttonPlayAgain.setOnClickListener {
            playGame()
        }

        playGame()
    }

    fun playGame() {
        buttonPlayAgain.visibility = View.GONE

        correctAnswers = 0

        scoreText.setText("Correct Answers: 0")

        // Get 3 random questions
        var questionPool = allQuestions.toMutableMap()

        while (remainingQuestions.size < 3) {
            var randomIndex = Random.nextInt(0, questionPool.size)

            var question = questionPool.entries.elementAt(randomIndex)
            remainingQuestions.put(question.key, question.value)

            questionPool.remove(question.key)
        }

        buttonA.visibility = View.VISIBLE
        buttonB.visibility = View.VISIBLE
        buttonC.visibility = View.VISIBLE

        // Display the first question
        questionText.setText(remainingQuestions.entries.first().key)
    }

    fun showToast(answer : Int) {
        if (answer == remainingQuestions.entries.first().value) {
            Toast.makeText(this, "CORRECT!", Toast.LENGTH_SHORT).show()
            correctAnswers += 1
            scoreText.setText("Correct Answers: " + correctAnswers)
        }
        else {
            Toast.makeText(this, "WRONG!", Toast.LENGTH_SHORT).show()
        }
        updateCurrentQuestion()
    }

    fun updateCurrentQuestion() {
        remainingQuestions.remove(remainingQuestions.entries.first().key)

        if (remainingQuestions.size > 0) {
            questionText.setText(remainingQuestions.entries.first().key)
        }
        else {
            buttonA.visibility = View.GONE
            buttonB.visibility = View.GONE
            buttonC.visibility = View.GONE

            buttonPlayAgain.visibility = View.VISIBLE

            var endGameText = ""

            if (correctAnswers >= 2) {
                endGameText = "You Win!\n\nYou got " + correctAnswers + " out of 3 questions right!"
            }
            else {
                endGameText = "You lost...\n\nYou only got " + correctAnswers + " out of 3 questions correct."
            }

            questionText.setText(endGameText)
        }
    }
}