import android.view.View

class ComputerPaddle(
    private val computerPaddleView: View,  // View ракетки компьютера
    private val screenWidth: Int,
    private val ball: Ball,
    private val speed: Float = 15f
) {
    fun update() {

        val ballCenterX = ball.x + ball.width / 2f
        val paddleCenterX = computerPaddleView.x + computerPaddleView.width / 2f

        // Определяем направление движения
        when {
            paddleCenterX < ballCenterX -> {
                computerPaddleView.x = (computerPaddleView.x + speed).coerceAtMost(screenWidth - computerPaddleView.width.toFloat())
            }
            paddleCenterX > ballCenterX -> {
                computerPaddleView.x = (computerPaddleView.x - speed).coerceAtLeast(0f)
            }
        }
    }

    fun getView(): View {
        return computerPaddleView
    }
}