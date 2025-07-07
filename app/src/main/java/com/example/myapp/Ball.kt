package com.example.myapp

import android.view.View
import kotlin.math.abs
import kotlin.random.Random

class Ball(
    private val ballView: View,
    private val screenWidth: Int,
    private val screenHeight: Int
) {
    var speedX = 10f
    var speedY = 10f

    val x: Float get() = ballView.x
    val y: Float get() = ballView.y
    val width: Float get() = ballView.width.toFloat()
    val height: Float get() = ballView.height.toFloat()

    fun update() {
        // Обновляем позицию мяча
        ballView.x += speedX
        ballView.y += speedY

        // Проверяем столкновение с левой/правой стенкой
        if (x <= 0 || x >= screenWidth - width) {
            speedX *= -1  // Отскок
        }

        // Проверяем выход за верхнюю/нижнюю границу (гол)
        if (y <= 0 || y >= screenHeight - height) {
            return  // В GameEngine обрабатываем гол
        }
    }

    fun checkPaddleCollision(paddle: View): Boolean {
        return (x + width >= paddle.x &&
                x <= paddle.x + paddle.width &&
                y + height >= paddle.y &&
                y <= paddle.y + paddle.height)
    }

    fun bounceOffPaddle() {
        speedY *= -1
        speedX += (Random.nextFloat() - 0.5f) * 2  // Добавляем случайность
    }

    fun reset(towardsPlayer: Boolean) {
        ballView.x = screenWidth / 2f - width / 2
        ballView.y = screenHeight / 2f - height / 2
        speedY = if (towardsPlayer) abs(speedY) else -abs(speedY)
        speedX = (if (Random.nextBoolean()) 1 else -1) * abs(speedX)
    }
}