package com.example.explooapp.ru.data.model

data class TestResponse(
    val statistic: StatisticResponse
)

data class StatisticResponse(
    val incomes: String,
    val outcomes: String,
    val delta: String
)

data class HomeWorkResponse(
    val page: PageResponse
)

data class PageResponse(
    val content: List<ContentResponse>
)

data class ContentResponse(
    val title: String,
    val financialImpact: String
)



