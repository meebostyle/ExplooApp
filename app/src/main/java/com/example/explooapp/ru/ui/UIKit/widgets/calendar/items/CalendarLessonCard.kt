package com.example.explooapp.ru.ui.UIKit.widgets.calendar.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.alfatesttask.ui.theme.ForegroundMuted
import com.example.alfatesttask.ui.theme.Primary
import com.example.alfatesttask.ui.theme.onestFontFamily
import com.example.explooapp.R

@Preview(
    showBackground = true,

)
@Composable
fun CalendarLessonCard(height: Dp = 120.dp,
                       topOffset: Dp = 0.dp,
                       timeTextWidth: Dp = 8.dp,
                       color: Color = Primary){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .offset(y = topOffset)
            .padding(start = timeTextWidth + 8.dp, top = 1.dp, end = 2.dp)
            .zIndex(1f),
        shape = RoundedCornerShape(6.dp),
        color = color.copy(0.4f)
    ){
        Row(modifier = Modifier.fillMaxSize()
            .padding(vertical = 4.dp, horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically){
            Box(modifier = Modifier
                .fillMaxHeight()
                .width(3.dp)
                .clip(RoundedCornerShape(100))
                .background(color = color)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier
                .fillMaxHeight()) {
                Text(
                    text = "Петя 8кл",
                    fontFamily = onestFontFamily,
                    fontSize = 10.sp,
                    color = color,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 10.sp // подберите значение для вашего шрифта
                    )
                Spacer(modifier = Modifier.height(2.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        modifier = Modifier.size(10.dp),
                        painter = painterResource(R.drawable.ic_plus),
                        contentDescription = "",
                        tint = color.copy(0.6f)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "15:30 - 17:00",
                        fontFamily = onestFontFamily,
                        fontSize = 10.sp,
                        color = color.copy(0.6f),
                        fontWeight = FontWeight.Normal,
                        lineHeight = 10.sp // подберите значение для вашего шрифта
                    )
                }

            }
            Box(modifier = Modifier.fillMaxSize().padding(top = 2.dp),
                contentAlignment = Alignment.TopEnd){
                Icon(
                    modifier = Modifier.size(10.dp),
                    painter = painterResource(R.drawable.ic_plus),
                    contentDescription = "",
                    tint = color.copy(0.6f)
                )
            }
        }
    }
}