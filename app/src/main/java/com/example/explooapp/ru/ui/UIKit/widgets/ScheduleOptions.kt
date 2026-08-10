package com.example.explooapp.ru.ui.UIKit.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alfatesttask.ui.theme.ForegroundMuted
import com.example.alfatesttask.ui.theme.onestFontFamily
import com.example.explooapp.R
import com.example.explooapp.ru.domain.schedule.LessonsList
import com.example.explooapp.ru.domain.schedule.toListItem
import com.example.explooapp.ru.ui.UIKit.items.TextInputField
import com.example.explooapp.ru.ui.UIKit.items.buttons.RoomButton
import com.example.explooapp.ru.ui.UIKit.items.dropdown.boards.DropdownBoards
import com.example.explooapp.ru.ui.UIKit.items.dropdown.lessonList.DropdownListForLessonMenu
import com.example.explooapp.ru.ui.UIKit.items.dropdown.dateandtime.DatePickerField
import com.example.explooapp.ru.ui.UIKit.items.dropdown.dateandtime.TimePickerField
import com.example.explooapp.ru.ui.UIKit.items.dropdown.repeatable.RepeatableDropDownList
import com.example.explooapp.ru.ui.UIKit.items.pickers.StudentsPicker
import com.example.explooapp.ru.ui.UIKit.items.pickers.TimeDurationPicker

@Preview(
    showBackground = true
)
@Composable
fun ScheduleOptions() {
    val scrollState = rememberScrollState()
    var lessonTitle by remember { mutableStateOf("") }
    var themeTitle by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                focusManager.clearFocus()
            }
    ) {
        Column(modifier = Modifier.fillMaxSize()) { // ← внешний Column на весь экран
            // Шапка (не скроллится)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Новое занятие",
                        fontFamily = onestFontFamily,
                        fontSize = 20.sp,
                        fontWeight = FontWeight(600)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Заполните информацию о занятии",
                        fontFamily = onestFontFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = ForegroundMuted
                    )
                }
                Text(text = "X")
            }
            Column(modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)) {

                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(12.dp),
                        painter = painterResource(R.drawable.ic_plus),
                        contentDescription = "",
                        tint = ForegroundMuted
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Предмет",
                        fontFamily = onestFontFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = ForegroundMuted
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))

                DropdownListForLessonMenu(LessonsList.entries.map { it.toListItem()})

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(12.dp),
                        painter = painterResource(R.drawable.ic_plus),
                        contentDescription = "",
                        tint = ForegroundMuted
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Название и тема",
                        fontFamily = onestFontFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = ForegroundMuted
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))

                TextInputField(
                    value = lessonTitle,
                    onValueChange = {
                        lessonTitle = it
                    },
                    placeholder = "Название занятия (необязательно)"
                )

                Spacer(modifier = Modifier.height(8.dp))
                TextInputField(
                    value = themeTitle,
                    onValueChange = {
                        themeTitle = it
                    },
                    placeholder = "Название темы (необязательно)"
                )
                Spacer(modifier = Modifier.height(12.dp))
                Box(modifier = Modifier.fillMaxWidth()
                    .height(0.5.dp)
                    .background(ForegroundMuted))
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(12.dp),
                        painter = painterResource(R.drawable.ic_plus),
                        contentDescription = "",
                        tint = ForegroundMuted
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Дата, время и длительность",
                        fontFamily = onestFontFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = ForegroundMuted
                    )

                }
                Spacer(modifier = Modifier.height(12.dp))

                Row() {
                    Box(modifier = Modifier.weight(2.5f)){
                        DatePickerField()
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(modifier = Modifier.weight(1f)){
                        TimePickerField()
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                TimeDurationPicker()
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(12.dp),
                        painter = painterResource(R.drawable.ic_plus),
                        contentDescription = "",
                        tint = ForegroundMuted
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Повторяемость",
                        fontFamily = onestFontFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = ForegroundMuted
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                RepeatableDropDownList()
                Spacer(modifier = Modifier.height(12.dp))
                Box(modifier = Modifier.fillMaxWidth()
                    .height(0.5.dp)
                    .background(ForegroundMuted))
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(12.dp),
                        painter = painterResource(R.drawable.ic_plus),
                        contentDescription = "",
                        tint = ForegroundMuted
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Ученики",
                        fontFamily = onestFontFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = ForegroundMuted
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                StudentsPicker()
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(12.dp),
                        painter = painterResource(R.drawable.ic_plus),
                        contentDescription = "",
                        tint = ForegroundMuted
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Доска",
                        fontFamily = onestFontFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = ForegroundMuted
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                DropdownBoards()
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(12.dp),
                        painter = painterResource(R.drawable.ic_plus),
                        contentDescription = "",
                        tint = ForegroundMuted
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Задание",
                        fontFamily = onestFontFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = ForegroundMuted
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                StudentsPicker()
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(12.dp),
                        painter = painterResource(R.drawable.ic_plus),
                        contentDescription = "",
                        tint = ForegroundMuted
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Ссылка на звонок",
                        fontFamily = onestFontFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = ForegroundMuted
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                RoomButton()

            }





    }
}
}