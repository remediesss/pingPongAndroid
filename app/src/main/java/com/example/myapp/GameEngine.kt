package com.example.myapp

import android.view.View
import android.widget.TextView

class GameEngine(
    private val ball: Ball,
    private val playerPaddle: PlayerPaddle,
    private val computerPaddle: ComputerPaddle,
    private val playerScoreView: TextView,
    private val computerScoreView: TextView,
    private val screenHeight: Int
) {
    private var playerScore = 0
    private var computerScore = 0

    fun update() {
        ball.update()
        computerPaddle.update()

        if (ball.checkPaddleCollision(playerPaddle.getView())) {
            ball.bounceOffPaddle()
        } else if (ball.checkPaddleCollision(computerPaddle.getView())) {
            ball.bounceOffPaddle()
        }

        if (ball.y >= screenHeight - ball.height) {
            computerScore++
            computerScoreView.text = computerScore.toString()
            ball.reset(false)
        } else if (ball.y <= 0) {
            playerScore++
            playerScoreView.text = playerScore.toString()
            ball.reset(true)
        }
    }
}