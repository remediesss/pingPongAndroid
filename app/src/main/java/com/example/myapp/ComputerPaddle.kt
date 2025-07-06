import android.view.View

class ComputerPaddle(
    private val view: View,  // View ракетки компьютера
    private val screenWidth: Int,
    private val ball: Ball,
    private val speed: Float = 15f
) {
    fun update() {

        val ballCenterX = ball.x + ball.width / 2f
        val paddleCenterX = view.x + view.width / 2f

        // Определяем направление движения
        when {
            paddleCenterX < ballCenterX -> {
                view.x = (view.x + speed).coerceAtMost(screenWidth - view.width.toFloat())
            }
            paddleCenterX > ballCenterX -> {
                view.x = (view.x - speed).coerceAtLeast(0f)
            }
        }
    }
}