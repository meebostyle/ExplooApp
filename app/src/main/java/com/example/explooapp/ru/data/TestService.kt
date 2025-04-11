package com.example.explooapp.ru.data

import com.example.explooapp.ru.data.model.HomeWorkResponse
import com.example.explooapp.ru.data.model.TestResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TestService {

    @Headers("Authorization: $TEST_TOKEN")
    @GET("tickets")
    fun getTickets(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("objectId") objectId: String
    ): Call<TestResponse>


    @Headers("Authorization: $TEST_TOKEN")
    @GET("tickets")
    fun getTitle(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("objectId") objectId: String
    ): Call<HomeWorkResponse>

    companion object {
        const val TEST_TOKEN =
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkOTM1OGY0Ny0wNjY5LTQ5NTktOGQ2NC1hMGY1OGFhMmQ4MzciLCJpYXQiOjE3MzgzMTYyNjMsImV4cCI6MTc0Njk1NjI2M30.P4j41kb-dH1LGzoXZY2K8Ev7fkkJN9gMjmNFgW5-RhI"
    }
}