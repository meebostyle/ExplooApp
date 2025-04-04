package com.example.explooapp.ru.data.model

data class TestResponse(
    val statistic: StatisticResponse
)

data class StatisticResponse(
    val incomes: String,
    val outcomes: String,
    val delta: String
)
