package com.example.explooapp.ru.domain.model

import com.example.explooapp.ru.data.model.ContentResponse

data class Content(
    val title: String,
    val financialImpact: String
)

fun ContentResponse.toContent(): Content {
    return Content(
        title = title,
        financialImpact = financialImpact
    )
}