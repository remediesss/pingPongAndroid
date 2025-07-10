package com.example.myapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    private lateinit var gameEngine: GameEngine
    private lateinit var handler: Handler
    private var isGameRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainView = findViewById<ConstraintLayout>(R.id.main)
        val ballView = findViewById<View>(R.id.ball)
        val playerPaddleView = findViewById<View>(R.id.playerPaddle)
        val computerPaddleView = findViewById<View>(R.id.computerPaddle)
        val playerScoreView = findViewById<TextView>(R.id.playerScore)
        val computerScoreView = findViewById<TextView>(R.id.computerScore)

        mainView.post {
            val ball = Ball(
                ballView,
                mainView.width,
                mainView.height
            )
            val playerPaddle = PlayerPaddle(playerPaddleView, mainView.width)
            val computerPaddle = ComputerPaddle(computerPaddleView, mainView.width, ball)

            gameEngine = GameEngine(
                ball,
                playerPaddle,
                computerPaddle,
                playerScoreView,
                computerScoreView,
                mainView.height
            )

            mainView.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_MOVE) {
                    playerPaddle.move(event.x)
                }
                true
            }

            handler = Handler(Looper.getMainLooper())
            handler.postDelayed(gameLoop, 16L)
        }

        mainView.postDelayed({
            isGameRunning = true
            handler = Handler(Looper.getMainLooper())
            handler.post(gameLoop)
        }, 500)
    }

    private val gameLoop = object : Runnable {
        override fun run() {
            if (isGameRunning) {
                gameEngine.update()
                handler.postDelayed(this, 16L)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        isGameRunning = false
    }

    override fun onResume() {
        super.onResume()
        if (!isGameRunning && ::handler.isInitialized) {
            isGameRunning = true
            handler.post(gameLoop)
        }
    }

    override fun onDestroy() {
        isGameRunning = false
        if (::handler.isInitialized) {
            handler.removeCallbacks(gameLoop)
        }
        super.onDestroy()
    }
}