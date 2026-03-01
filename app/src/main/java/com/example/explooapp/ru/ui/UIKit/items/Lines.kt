package com.example.explooapp.ru.ui.UIKit.items

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.alfatesttask.ui.theme.Primary

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun WavyLine(
    modifier: Modifier = Modifier
        .fillMaxWidth()
) {
    Box(
        modifier = modifier
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            val path = Path()
            val amplitude = 5f // высота волны
            val frequency = 0.007f // частота волны

            // Начинаем с левого края по центру
            path.moveTo(0f, canvasHeight / 2)

            // Рисуем волну
            for (x in 0..canvasWidth.toInt() step 10) {
                val y = canvasHeight / 2 +
                        amplitude * kotlin.math.sin(x * frequency)
                path.lineTo(x.toFloat(), y)
            }

            drawPath(
                path = path,
                color = Primary,
                style = Stroke(
                    width = 3.dp.toPx(),
                    cap = StrokeCap.Round, // Добавьте эту строку для закругления концов
                    pathEffect = PathEffect.cornerPathEffect(90f) // радиус закругления углов
                )
            )
        }
    }
}