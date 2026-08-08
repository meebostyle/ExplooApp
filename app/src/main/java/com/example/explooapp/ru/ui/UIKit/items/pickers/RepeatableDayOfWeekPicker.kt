package com.example.explooapp.ru.ui.UIKit.items.pickers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alfatesttask.ui.theme.ForegroundMuted
import com.example.alfatesttask.ui.theme.Primary
import com.example.alfatesttask.ui.theme.onestFontFamily
import com.example.explooapp.ru.ui.UIKit.items.AnimatedColoredSurfaceSheet


@Preview(
    showBackground = true, backgroundColor = 0xFFFF0000
)
@Composable
fun DayOfWeekPicker(){
    val timeList = listOf("Пн", "Вт", "Ср", "Чт", "Пн", "Сб", "Вс")
    var choosenTime = ""
    LazyRow(){
        items(timeList.count()){
            TimeDurationPickerItem(timeList[it]){
                choosenTime = timeList[it]
            }
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}




@Composable
private fun TimeDurationPickerItem(time: String, clickable: (() -> Unit)){
    var isActive by remember { mutableStateOf(false) }
    AnimatedColoredSurfaceSheet(
        clickable = {
            isActive = !isActive
            clickable()
        },
        color = if (isActive) Primary else ForegroundMuted.copy(0.4f)
    ){
        Box(modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp),
            contentAlignment = Alignment.Center) {
            Text(modifier = Modifier,
                text = time,
                fontFamily = onestFontFamily,
                fontSize = 10.sp,
                fontWeight = FontWeight(400),
                color = ForegroundMuted)
        }
    }
}