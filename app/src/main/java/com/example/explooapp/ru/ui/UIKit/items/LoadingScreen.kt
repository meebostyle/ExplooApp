package com.example.explooapp.ru.ui.UIKit.items

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframesWithSpline
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.explooapp.R
import kotlinx.coroutines.launch

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LoadingScreenPlane() {
    val offsetX = remember { Animatable(0f) }
    val offsetY = remember { Animatable(0f) }
    val alpha = remember { Animatable(1f) }
    val trailOffset = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        launch {
            offsetX.animateTo(
                250f,
                animationSpec = infiniteRepeatable(
                    animation = keyframesWithSpline {
                        durationMillis = 1500
                        -100f at 0
                        -30f at 200
                        -30f at 400
                        150f at 1200
                        150f at 1500
                    },
                    repeatMode = RepeatMode.Restart
                )
            )
        }
        launch {
            offsetY.animateTo(
                -250f,
                animationSpec = infiniteRepeatable(
                    animation = keyframesWithSpline {
                        durationMillis = 1500
                        50f at 0
                        30f at 200
                        30f at 400
                        15f at 600
                        -40f at 900
                        -100f at 1200
                        -100f at 1500
                    },
                    repeatMode = RepeatMode.Restart
                )
            )
        }
        launch {
            alpha.animateTo(
                1f,
                animationSpec = infiniteRepeatable(
                    animation = keyframesWithSpline {
                        durationMillis = 1500
                        0.3f at 0
                        1f at 200
                        1f at 400
                        0.8f at 600
                        0.3f at 900
                        0.1f at 1200
                        0f at 1500
                    },
                    repeatMode = RepeatMode.Restart
                )
            )
        }

        // Анимация для следа
        launch {
            trailOffset.animateTo(
                1f,
                animationSpec = infiniteRepeatable(
                    animation = keyframesWithSpline {
                        durationMillis = 1500
                        0f at 0
                        0.3f at 200
                        0.5f at 400
                        0.7f at 600
                        0.9f at 900
                        1f at 1200
                        1f at 1500
                    },
                    repeatMode = RepeatMode.Restart
                )
            )
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Несколько изображений для создания следа
        for (i in 0..3) {
            val trailProgress = (trailOffset.value - i * 0.25f).coerceIn(0f, 1f)
            val trailX = -100f + (350f * trailProgress)
            val trailY = 50f + (-150f * trailProgress)

            Image(
                painter = painterResource(id = R.drawable.ic_plane),
                contentDescription = null,
                modifier = Modifier
                    .size((20 - i * 4).dp)
                    .offset(
                        x = (offsetX.value * trailProgress + trailX * (1f - trailProgress)).dp,
                        y = (offsetY.value * trailProgress + trailY * (1f - trailProgress)).dp
                    )
                    .rotate(60f)
                    .alpha((0.3f - i * 0.1f) * (1f - trailProgress))
            )
        }

        Image(
            painter = painterResource(id = R.drawable.ic_plane),
            contentDescription = "logo",
            modifier = Modifier
                .size(40.dp)
                .offset(x = offsetX.value.dp, y = offsetY.value.dp)
                .rotate(60f)
                .alpha(alpha.value)
        )
    }
}