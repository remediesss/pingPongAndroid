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

        if (ball.checkCollision(playerPaddle.getView())) {
            ball.reverseY()
        } else if (ball.checkCollision(computerPaddle.getView())) {
            ball.reverseY()
        }

        if (ball.y >= screenHeight - ball.height) {
            computerScore++
            computerScoreView.text = computerScore.toString()
            ball.reset()
        } else if (ball.y <= 0) {
            playerScore++
            playerScoreView.text = playerScore.toString()
            ball.reset()
        }
    }
}