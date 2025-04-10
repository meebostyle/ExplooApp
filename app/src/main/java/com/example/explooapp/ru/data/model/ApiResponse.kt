package com.example.explooapp.ru.data.model

data class ApiResponse(
    val statistic: StatisticResponse
)

data class StatisticResponse(
    val incomes: String,
    val outcomes: String,
    val delta: String
)



