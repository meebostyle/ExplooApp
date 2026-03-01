package com.example.explooapp.ru.ui.UIKit.backgounds


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alfatesttask.ui.theme.Background
import com.example.alfatesttask.ui.theme.ForegroundMuted
import com.example.alfatesttask.ui.theme.handwriteFontFamily
import kotlin.random.Random

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun MathBackground() {
    val leftTexts = listOf(
        "E = mc²",
        "∫f(x)dx",
        "a² + b² = c²"
    )

    val rightTexts = listOf(
        "sin²α + cos²α = 1",
        "Δ = b² - 4ac",
        "lim x→∞"
    )

    val leftOffsetsY = remember {
        List(3) { Random.nextInt(0, 100).dp }
    }

    val leftOffsetsX = remember {
        List(3) { Random.nextInt(0, 50).dp }
    }

    val rightOffsetsY = remember {
        List(3) { Random.nextInt(0, 100).dp }
    }
    val rightOffsetsX = remember {
        List(3) { Random.nextInt(0, 50).dp }
    }
    val rightRotate = remember {
        List(3) { Random.nextFloat() * 25 - 25 }
    }
    val leftRotate = remember {
        List(3) { Random.nextFloat() * 25 - 25 }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        DottedBackground()
        // Левая колонка
        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 20.dp)
                .fillMaxHeight(),
            Arrangement.SpaceEvenly

        ) {

            leftTexts.forEachIndexed { index, text ->

                Row {
                    Spacer(modifier = Modifier.width(leftOffsetsX[index]))
                    Text(
                        text = text,
                        color = ForegroundMuted,
                        modifier = Modifier
                            .rotate(leftRotate[index])
                            .alpha(0.25f),
                        fontFamily = handwriteFontFamily,
                        fontSize = 24.sp

                    )
                }
                Spacer(modifier = Modifier.height(leftOffsetsY[index]))

            }
        }

        // Правая колонка
        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp)
                .fillMaxHeight(),
            Arrangement.SpaceEvenly
        ) {
            rightTexts.forEachIndexed { index, text ->
                Spacer(modifier = Modifier.height(rightOffsetsY[index]))
                Row {
                    Spacer(modifier = Modifier.width(rightOffsetsX[index]))
                    Text(
                        text = text,
                        color = ForegroundMuted,
                        modifier = Modifier
                            .rotate(rightRotate[index])
                            .alpha(0.25f),
                        fontFamily = handwriteFontFamily,
                        fontSize = 24.sp
                    )

                }

            }

        }

    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DottedBackground() {
    // Цвет из вашей цветовой схемы
    val gridColor = ForegroundMuted// Используем Grid цвет

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .alpha(0.25f)
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        // Расстояние между точками
        val spacing = 30.dp.toPx()

        // Рисуем точки по всей сетке
        var x = spacing / 2
        while (x < canvasWidth) {
            var y = spacing / 2
            while (y < canvasHeight) {
                drawCircle(
                    color = gridColor,
                    radius = 1.dp.toPx(),
                    center = Offset(x, y)
                )
                y += spacing
            }
            x += spacing
        }
    }
}