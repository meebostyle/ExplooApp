package com.example.explooapp.ru.data.model

data class TestResponse(
    val statistic: StatisticResponse,
    val page: PageResponse
)

data class StatisticResponse(
    val incomes: String,
    val outcomes: String,
    val delta: String
)

data class PageResponse(
    val content: List<ContentResponse>
)

data class ContentResponse(
    val title: String,
    val financialImpact: String
)