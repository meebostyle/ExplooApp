package com.example.explooapp.ru.data

import com.example.explooapp.ru.data.model.TestResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TestRepository {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://services-mskstroymir.ru/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(TestService::class.java)

    suspend fun getTickets(page: Int, limit: Int, objectId: String): TestResponse {
        return service.getTickets(page, limit, objectId)
    }
}