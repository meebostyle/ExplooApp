package com.example.explooapp.ru.ui.UIKit.widgets.schedule

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.calculateZoom
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.example.alfatesttask.ui.theme.ForegroundMuted
import com.example.alfatesttask.ui.theme.Primary
import com.example.explooapp.ru.ui.UIKit.widgets.schedule.items.CalendarLessonCard

// ---------- STATEFUL ----------

@Composable
fun ScheduleSingleDayWidget(
    lessons: List<LessonModel>,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    var scale by remember { mutableStateOf(1f) }
    val minScale = 0.9f
    val maxScale = 3f

    ScheduleSingleDayContent(
        lessons = lessons,
        scale = scale,
        scrollState = scrollState,
        onZoom = { zoomChange ->
            scale = (scale * zoomChange).coerceIn(minScale, maxScale)
        },
        modifier = modifier,
    )
}


// ---------- STATELESS ----------
@Composable
fun ScheduleSingleDayContent(
    lessons: List<LessonModel>,
    scale: Float,
    scrollState: ScrollState,
    onZoom: (Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current
    val textMeasurer = rememberTextMeasurer()
    val textStyle = TextStyle(fontSize = 12.sp)
    val sampleTime = "23:00"
    val textLayout = textMeasurer.measure(sampleTime, textStyle)
    val timeTextWidth = with(density) { textLayout.size.width.toDp() }
    val timeTextHeight = with(density) { textLayout.size.height.toDp() }

    val hourHeight = 48.dp * scale
    val startHour = 5
    val lineOffsetY = timeTextHeight / 2

    Box(
        modifier = modifier
            .verticalScroll(scrollState)
            .pointerInput(Unit) {
                awaitEachGesture {
                    awaitFirstDown(requireUnconsumed = false)
                    do {
                        val event = awaitPointerEvent()
                        val pressedCount = event.changes.count { it.pressed }
                        if (pressedCount > 1) {
                            val zoomChange = event.calculateZoom()
                            if (zoomChange != 1f) onZoom(zoomChange)
                            event.changes.forEach { if (it.positionChanged()) it.consume() }
                        }
                    } while (event.changes.any { it.pressed })
                }
            }
    ) {
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
            val topOffset =
                (hour - startHour) * hourHeight + lineOffsetY + (minute / 60f) * hourHeight

            CalendarLessonCard(
                height = (getHeightFromDuration(lesson.duration) - 2).dp * scale,
                topOffset = topOffset,
                timeTextWidth = timeTextWidth,
                color = Primary
            )
        }
    }
}


private val previewLessons = listOf(
    LessonModel(name = "Математика", time = "16:00", duration = 120),
    LessonModel(name = "Английский язык", time = "09:00", duration = 90),
)

@Preview(showBackground = true)
@Composable
private fun ScheduleSingleDayWidgetPreview() {
    ScheduleSingleDayWidget(
        lessons = previewLessons,
        modifier = Modifier.height(600.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun ScheduleSingleDayContentPreview() {
    ScheduleSingleDayContent(
        lessons = previewLessons,
        scale = 1f,
        scrollState = rememberScrollState(),
        onZoom = {},
        modifier = Modifier.height(600.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun ScheduleSingleDayContentZoomedPreview() {
    ScheduleSingleDayContent(
        lessons = previewLessons,
        scale = 2f,
        scrollState = rememberScrollState(),
        onZoom = {},
        modifier = Modifier.height(600.dp)
    )
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


data class LessonModel(
    val name: String,
    val time: String,
    val duration: Int
)

