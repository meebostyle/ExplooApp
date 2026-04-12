package com.example.explooapp.ru.ui.UIKit.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alfatesttask.ui.theme.ForegroundMuted
import com.example.alfatesttask.ui.theme.onestFontFamily

@Preview(showBackground = true)
@Composable
fun DropDownListItem(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    text: String = "Математика",
    fontFamily: FontFamily = onestFontFamily,
    fontWeight: FontWeight = FontWeight(400),
    fontSize: TextUnit = 12.sp,
    color: Color = ForegroundMuted,
    label: Int? = null
) {
    Box(modifier = modifier.defaultMinSize(200.dp, 50.dp)) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            shadowElevation = 4.dp,
            contentColor = Color.Transparent
        ) {

            Button(
                onClick = onClick,
                modifier = modifier.fillMaxWidth(),
                colors = ButtonColors(
                    Color.Transparent,
                    Color.Transparent,
                    Color.Transparent,
                    Color.Transparent
                ),
                shape = RoundedCornerShape(16.dp),
                contentPadding = PaddingValues(horizontal = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    if (label != null) {
                        Icon(
                            painter = painterResource(label),
                            tint = ForegroundMuted,
                            contentDescription = "",
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(
                            modifier = Modifier
                                .width(8.dp)
                        )
                    }

                    Text(
                        text = text,
                        fontFamily = fontFamily,
                        fontWeight = fontWeight,
                        fontSize = fontSize,
                        maxLines = 1,
                        color = color

                    )

                }
            }


        }
    }
}