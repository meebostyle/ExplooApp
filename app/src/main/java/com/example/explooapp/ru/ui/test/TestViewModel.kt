package com.example.explooapp.ru.ui.test

import androidx.lifecycle.ViewModel
import com.example.explooapp.ru.data.TestRepository
import com.example.explooapp.ru.data.model.TestResponse
import com.example.explooapp.ru.domain.model.Content
import com.example.explooapp.ru.domain.model.toContent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TestViewModel : ViewModel() {

    private val testRepository = TestRepository()

    val contentFlow = MutableStateFlow<List<Content>>(
        listOf()
    )

    val statisticFlow = MutableStateFlow("")

    val errorFlow = MutableSharedFlow<Boolean>(extraBufferCapacity = 1)

    fun getStatistic() {
        testRepository.getTickets(page = 0, limit = 15, objectId = "101")
            .enqueue(
                object : Callback<TestResponse> {
                    override fun onResponse(p0: Call<TestResponse>, p1: Response<TestResponse>) {
                        p1.body()?.let { testResponse ->
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

                    override fun onFailure(p0: Call<TestResponse>, p1: Throwable) {
                        errorFlow.tryEmit(true)
                    }
                }
            )
    }
}