package com.example.explooapp.ru.ui.UIKit.widgets.calendar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.compose.ui.zIndex
import com.example.alfatesttask.ui.theme.ForegroundMuted
import com.example.alfatesttask.ui.theme.Primary

@Preview(
    showBackground = true
)
@Composable
fun SingleDayCalendarView(){
    val scrollState = rememberScrollState()
    val lessons = mutableListOf(LessonModel(
        name = "Математика",
        time = "16:00",
        duration = 120
    ),
        LessonModel(
            name = "Английский язык",
            time = "09:00",
            duration = 90
        )
    )
    val density = LocalDensity.current
    val textMeasurer = rememberTextMeasurer()
    val textStyle = TextStyle(fontSize = 12.sp)
    val sampleTime = "23:00"
    val textLayout = textMeasurer.measure(sampleTime, textStyle)
    val timeTextWidth = with(density) { textLayout.size.width.toDp() }
    val timeTextHeight = with(density) { textLayout.size.height.toDp() }

    val hourHeight = 48.dp
    val startHour = 5
    val lineOffsetY = timeTextHeight / 2

    Box(modifier = Modifier.verticalScroll(scrollState)) {
        Column {
            repeat(20) { index ->
                Box(modifier = Modifier.height(hourHeight)) {
                    Row(
                        modifier = Modifier.align(Alignment.TopStart),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = simpleDateFormate(index), fontSize = 12.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(ForegroundMuted.copy(0.4f))
                        )
                    }
                }
            }
        }

        lessons.forEach { lesson ->
            val hour = getIntHoursFromTime(lesson.time)
            val minute = getIntMinutesFromTime(lesson.time)
            val topOffset = (hour - startHour) * hourHeight + lineOffsetY + (minute / 60f) * hourHeight
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((getHeightFromDuration(lesson.duration)-2).dp)
                    .offset(y = topOffset)
                    .padding(start = timeTextWidth + 8.dp, top = 1.dp)
                    .zIndex(1f),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Primary),
                color = Primary.copy(0.4f)
            ) {
                // содержимое урока
            }
        }
    }
}

private fun simpleDateFormate(extraTime: Int): String{
    val result = (5+extraTime).toString()
    return "${if (result.length < 2) "0$result" else result}:00"
}

private fun getHeightFromDuration(duration: Int): Int{
    return duration/15*12
}

private fun getIntHoursFromTime(time: String): Int{
    return time.substring(0,2).toInt()
}

private fun getIntMinutesFromTime(time: String): Int{
    return time.substring(3).toInt()
}
fun getOffsetFromDuration(time: String): Int{
    val hours = getIntHoursFromTime(time)
    val minutes = getIntMinutesFromTime(time)
    val offsetSpacers = ((hours-5)*60+minutes) / 15 * 8
    val offsetLines = (hours - 4)* 12
    return offsetSpacers+offsetLines
}

data class LessonModel(
    val name: String,
    val time: String,
    val duration: Int
)

