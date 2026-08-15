package com.example.explooapp.ru.ui.UIKit.items.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alfatesttask.ui.theme.Background
import com.example.alfatesttask.ui.theme.FeatureSchedule
import com.example.alfatesttask.ui.theme.Primary
import com.example.alfatesttask.ui.theme.handwriteFontFamily
import com.example.alfatesttask.ui.theme.onestFontFamily

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

@Preview(showBackground = true)
@Composable
fun SquareButton(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    text: String = "Новое занятие",
    fontFamily: FontFamily = onestFontFamily,
    fontWeight: FontWeight = FontWeight(400),
    fontSize: TextUnit = 16.sp,
    color: Color = FeatureSchedule,
    label: Int? = null
) {
    Box(modifier = modifier) {
        Button(
            onClick = onClick,
            modifier = modifier,
            colors = ButtonColors(color, Background, color, Background),
            elevation = ButtonDefaults.elevatedButtonElevation(),
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (label != null) {
                    Image(
                        painter = painterResource(label),
                        contentDescription = "",
                        Modifier.size(12.dp)
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
                    maxLines = 1

                )

            }

        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun ListPicker(
    modifier: Modifier = Modifier
        .fillMaxWidth()
) {
    var widthInDp by remember { mutableStateOf(0) }
    var expanded by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    Box(modifier = modifier.onGloballyPositioned { layoutCoordinaties ->
        widthInDp = layoutCoordinaties.size.width

    }
    )
    {

        DropDownListItem(onClick = {
            expanded = true
        })
        DropdownMenu(
            expanded = true,
            onDismissRequest = { expanded = false },
            scrollState = scrollState,
            containerColor = Color.Transparent,
            shadowElevation = 0.dp,
            modifier = Modifier.width(widthInDp.dp),
        ) {
            repeat(10) {
                DropDownListItem(onClick = {
                    expanded = false
                })
                Spacer(modifier = Modifier.height(4.dp))
            }

        }
    }

    LaunchedEffect(expanded) {
        if (expanded) {
            scrollState.scrollTo(scrollState.maxValue)
        }
    }
}