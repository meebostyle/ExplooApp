package com.example.explooapp.ru.ui.UIKit.items

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alfatesttask.ui.theme.Background
import com.example.alfatesttask.ui.theme.Primary
import com.example.alfatesttask.ui.theme.handwriteFontFamily

@Preview(showBackground = true)
@Composable
fun NextButton(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
        .width(128.dp)
        .height(48.dp),
    text: String = "Button",
    fontFamily: FontFamily = handwriteFontFamily,
    fontWeight: FontWeight = FontWeight(400),
    fontSize: TextUnit = 20.sp,
) {
    Box(modifier = modifier) {
        Button(
            onClick = onClick,
            modifier = modifier,
            colors = ButtonColors(Primary, Background, Primary, Background),
            elevation = ButtonDefaults.elevatedButtonElevation()
        ) {
            Text(
                text = text,
                fontFamily = fontFamily,
                fontWeight = fontWeight,
                fontSize = fontSize,
                maxLines = 1

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RectangleNextButton(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
        .width(128.dp)
        .height(48.dp),
    text: String = "Button",
    fontFamily: FontFamily = handwriteFontFamily,
    fontWeight: FontWeight = FontWeight(400),
    fontSize: TextUnit = 20.sp,
    shape: Shape = ButtonDefaults.shape
) {
    Box(modifier = modifier) {
        Button(
            onClick = onClick,
            shape = shape,
            modifier = modifier,
            colors = ButtonColors(Primary, Background, Primary, Background),
            elevation = ButtonDefaults.elevatedButtonElevation()
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 40.dp),
                text = text,
                fontFamily = fontFamily,
                fontWeight = fontWeight,
                fontSize = fontSize,
                maxLines = 1

            )
        }
    }
}