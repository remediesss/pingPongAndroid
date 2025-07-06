import android.view.View

class PlayerPaddle(
    private val playerPaddleView: View,  // View ракетки игрока
    private val screenWidth: Int
) {
    fun move(newX: Float) {
        playerPaddleView.x = newX.coerceIn(0f, screenWidth - playerPaddleView.width.toFloat())
    }

    fun getView(): View {
        return playerPaddleView
    }
}