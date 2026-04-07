package com.example.explooapp.ru.domain.schedule

import androidx.compose.ui.graphics.Color
import com.example.alfatesttask.ui.theme.ForegroundMuted
import com.example.explooapp.R
import com.example.explooapp.ru.ui.UIKit.items.dropdown.ItemModel

enum class LessonsList(
    val icon: Int,
    val lesson: String,
    val color: Color
) {
    NoChosen(icon = R.drawable.ic_vk, lesson = "Без предмета", color = ForegroundMuted),
    Eng(icon = R.drawable.ic_vk, lesson = "Английский язык", color = Color(46, 204, 113)),
    Bio(icon = R.drawable.ic_vk, lesson = "Биология", color = Color(28, 188, 156)),
    Geo(icon = R.drawable.ic_vk, lesson = "География", color = Color(0, 184, 148)),
    Art(icon = R.drawable.ic_vk, lesson = "ИЗО", color = Color(255, 118, 117)),
    ComputerScience(icon = R.drawable.ic_vk, lesson = "Информатика", color = Color(9, 132, 227)),
    Spain(icon = R.drawable.ic_vk, lesson = "Испанский", color = Color(253, 121, 168)),
    History(icon = R.drawable.ic_vk, lesson = "История", color = Color(243, 156, 18)),
    Chinese(icon = R.drawable.ic_vk, lesson = "Китайский", color = Color(214, 48, 49)),
    Literature(icon = R.drawable.ic_vk, lesson = "Литература", color = Color(108, 92, 231)),
    Music(icon = R.drawable.ic_vk, lesson = "Музыка", color = Color(0, 206, 201)),
    German(icon = R.drawable.ic_vk, lesson = "Немецкий язык", color = Color(253, 203, 110)),
    Social(icon = R.drawable.ic_vk, lesson = "Обществознание", color = Color(232, 67, 147)),
    Math(icon = R.drawable.ic_vk, lesson = "Математика", color = Color(52, 152, 219)),
    Prog(icon = R.drawable.ic_vk, lesson = "Программирование", color = Color(99, 110, 114)),
    Rus(icon = R.drawable.ic_vk, lesson = "Русский язык", color = Color(231, 76, 60)),
    Phys(icon = R.drawable.ic_vk, lesson = "Физика", color = Color(155, 89, 182)),
    French(icon = R.drawable.ic_vk, lesson = "Французский язык", color = Color(162, 155, 254)),
    Chemistry(icon = R.drawable.ic_vk, lesson = "Химия", color = Color(230, 126, 34)),
}

fun LessonsList.toListItem(): ItemModel {
    return (ItemModel(
        icon = icon,
        text = lesson,
        color = color,
    ))
}