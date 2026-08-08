package com.example.explooapp.ru.ui.UIKit.items

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.alfatesttask.ui.theme.ForegroundMuted

@Composable
fun AnimatedSurfaceSheet(clickable: (() -> Unit), content: @Composable (() -> Unit)){
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val animateScale = animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f
    )
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .scale(animateScale.value)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                clickable()
            }
            ,
        color = ForegroundMuted.copy(0.05f),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, ForegroundMuted.copy(0.2f))
    ){
        content()
    }
}

@Composable
fun AnimatedColoredSurfaceSheet(clickable: (() -> Unit), color: Color, content: @Composable (() -> Unit)){
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val animateScale = animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f
    )
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .scale(animateScale.value)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                clickable()
            }
        ,
        color = color.copy(0.05f),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, color.copy(0.2f))
    ){
        content()
    }
}

