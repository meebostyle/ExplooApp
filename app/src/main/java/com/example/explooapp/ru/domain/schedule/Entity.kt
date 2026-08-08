package com.example.explooapp.ru.domain.schedule

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.alfatesttask.ui.theme.ForegroundMuted
import com.example.explooapp.ru.ui.UIKit.items.dropdown.lessonList.DropdownLessonListItemModel
import com.example.explooapp.ru.ui.UIKit.items.icons.ScheduleIcons
import com.example.explooapp.ru.ui.UIKit.items.icons.art
import com.example.explooapp.ru.ui.UIKit.items.icons.bio
import com.example.explooapp.ru.ui.UIKit.items.icons.chemistry
import com.example.explooapp.ru.ui.UIKit.items.icons.chinese
import com.example.explooapp.ru.ui.UIKit.items.icons.computerScience
import com.example.explooapp.ru.ui.UIKit.items.icons.eng
import com.example.explooapp.ru.ui.UIKit.items.icons.french
import com.example.explooapp.ru.ui.UIKit.items.icons.geo
import com.example.explooapp.ru.ui.UIKit.items.icons.german
import com.example.explooapp.ru.ui.UIKit.items.icons.history
import com.example.explooapp.ru.ui.UIKit.items.icons.literature
import com.example.explooapp.ru.ui.UIKit.items.icons.math
import com.example.explooapp.ru.ui.UIKit.items.icons.music
import com.example.explooapp.ru.ui.UIKit.items.icons.noChosen
import com.example.explooapp.ru.ui.UIKit.items.icons.phys
import com.example.explooapp.ru.ui.UIKit.items.icons.prog
import com.example.explooapp.ru.ui.UIKit.items.icons.rus
import com.example.explooapp.ru.ui.UIKit.items.icons.social
import com.example.explooapp.ru.ui.UIKit.items.icons.spain

enum class LessonsList(
    val icon: ImageVector?,
    val lesson: String,
    val color: Color
) {
    NoChosen(icon = ScheduleIcons.noChosen, lesson = "Без предмета", color = ForegroundMuted),
    Eng(icon = ScheduleIcons.eng, lesson = "Английский язык", color = Color(46, 204, 113)),
    Bio(icon = ScheduleIcons.bio, lesson = "Биология", color = Color(28, 188, 156)),
    Geo(icon = ScheduleIcons.geo, lesson = "География", color = Color(0, 184, 148)),
    Art(icon = ScheduleIcons.art, lesson = "ИЗО", color = Color(255, 118, 117)),
    ComputerScience(
        icon = ScheduleIcons.computerScience,
        lesson = "Информатика",
        color = Color(9, 132, 227)
    ),
    Spain(icon = ScheduleIcons.spain, lesson = "Испанский", color = Color(253, 121, 168)),
    History(icon = ScheduleIcons.history, lesson = "История", color = Color(243, 156, 18)),
    Chinese(icon = ScheduleIcons.chinese, lesson = "Китайский", color = Color(214, 48, 49)),
    Literature(icon = ScheduleIcons.literature, lesson = "Литература", color = Color(108, 92, 231)),
    Music(icon = ScheduleIcons.music, lesson = "Музыка", color = Color(0, 206, 201)),
    German(icon = ScheduleIcons.german, lesson = "Немецкий язык", color = Color(253, 203, 110)),
    Social(icon = ScheduleIcons.social, lesson = "Обществознание", color = Color(232, 67, 147)),
    Math(icon = ScheduleIcons.math, lesson = "Математика", color = Color(52, 152, 219)),
    Prog(icon = ScheduleIcons.prog, lesson = "Программирование", color = Color(99, 110, 114)),
    Rus(icon = ScheduleIcons.rus, lesson = "Русский язык", color = Color(231, 76, 60)),
    Phys(icon = ScheduleIcons.phys, lesson = "Физика", color = Color(155, 89, 182)),
    French(icon = ScheduleIcons.french, lesson = "Французский язык", color = Color(162, 155, 254)),
    Chemistry(icon = ScheduleIcons.chemistry, lesson = "Химия", color = Color(230, 126, 34)),
}

fun LessonsList.toListItem(): DropdownLessonListItemModel {
    return (DropdownLessonListItemModel(
        icon = icon,
        text = lesson,
        color = color,
    ))
}

