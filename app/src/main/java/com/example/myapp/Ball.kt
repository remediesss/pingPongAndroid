import android.view.View

class Ball(
    private val ballView: View,  // View мяча из XML
    private val screenWidth: Int,
    private val screenHeight: Int
) {
    val x: Float get() = ballView.x
    val y: Float get() = ballView.y
    val width: Int get() = ballView.width
    val height: Int get() = ballView.height

    private var speedX = 10f
    private var speedY = 10f

    fun update() {
        ballView.x += speedX
        ballView.y += speedY

        // Отскок от стен
        if (ballView.x <= 0 || ballView.x >= screenWidth - ballView.width) {
            speedX *= -1
        }
    }

    fun reset() {
        ballView.x = (screenWidth / 2 - ballView.width / 2).toFloat()
        ballView.y = (screenHeight / 2 - ballView.height / 2).toFloat()
        speedX = if (Math.random() < 0.5) 10f else -10f
        speedY *= -1
    }

    fun checkCollision(paddle: View): Boolean {
        return ballView.y + ballView.height >= paddle.y &&
                ballView.x + ballView.width >= paddle.x &&
                ballView.x <= paddle.x + paddle.width
    }

    fun reverseY() {
        speedY *= -1
    }
}