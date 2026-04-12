package com.example.explooapp.ru.ui.trash.test

import androidx.lifecycle.ViewModel
import com.example.explooapp.ru.data.TestRepository
import com.example.explooapp.ru.domain.model.Content
import com.example.explooapp.ru.domain.model.toContent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow


class TestViewModel : ViewModel() {

    private val testRepository = TestRepository()

    val contentFlow = MutableStateFlow<List<Content>>(
        listOf()
    )

    val statisticFlow = MutableStateFlow("")

    val errorFlow = MutableSharedFlow<Boolean>(extraBufferCapacity = 1)

    suspend fun getStatistic() {

            val testResponse = testRepository.getTickets(page = 0, limit = 15, objectId = "101")
            val statistic =
                "${testResponse.statistic.incomes} - ${testResponse.statistic.outcomes} = ${testResponse.statistic.delta}"
            val contents = testResponse.page.content.map { contentResponse ->
                contentResponse.toContent()
            }
                .shuffled()

            contentFlow.value = contents
            statisticFlow.value = statistic

    }

}