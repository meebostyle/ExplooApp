package com.example.explooapp.ru.ui.UIKit.widgets.schedule

import com.example.explooapp.ru.domain.schedule.LessonsList

data class ScheduleOptionsModel(
    val lesson: LessonsList,
    val name: String?,
    val theme: String?,
    val date: String,
    val time: String,
    val repeatable: String,
    val students: List<String>,
    val board: String,
    val task: List<String>,
    val link: String,
)
