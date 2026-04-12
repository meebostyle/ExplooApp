package com.example.explooapp.ru.ui.UIKit.items

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alfatesttask.ui.theme.ForegroundMuted
import com.example.alfatesttask.ui.theme.Primary
import com.example.alfatesttask.ui.theme.onestFontFamily


@Preview(
    showBackground = true,

    )
@Composable
fun TextInputField(
    value: String = "",
    onValueChange: (String) -> Unit = {},
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(
            horizontal = 16.dp,
            vertical = 12.dp
        ),
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    textStyle: TextStyle = TextStyle(
        fontFamily = onestFontFamily,
        fontSize = 13.sp,
        fontWeight = FontWeight(400),
    ),
    placeholder: String = "Название занятия (необязательно)"
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focused by interactionSource.collectIsFocusedAsState()

    val borderInAlpha = animateFloatAsState(
        targetValue = if (focused) 0.4f else 0.2f,
        animationSpec = tween(300)
    )
    val borderOutAlpha = animateFloatAsState(
        targetValue = if (focused) 0.6f else 0f,
        animationSpec = tween(300)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, Primary.copy(borderOutAlpha.value), RoundedCornerShape(14.dp)),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .padding(2.dp),
            color = Color.Transparent,
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, ForegroundMuted.copy(borderInAlpha.value))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = textStyle,
                        color = ForegroundMuted,
                        modifier = modifier
                    )
                }

                // Основное поле ввода
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    interactionSource = interactionSource,
                    enabled = enabled,
                    readOnly = readOnly,
                    singleLine = singleLine,
                    maxLines = maxLines,
                    textStyle = textStyle,
                    modifier = modifier
                )
            }
        }
    }
}