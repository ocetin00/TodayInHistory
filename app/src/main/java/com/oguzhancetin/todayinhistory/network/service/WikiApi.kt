package com.oguzhancetin.todayinhistory.network.service

import com.oguzhancetin.todayinhistory.model.WikiEventResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WikiApi {
    @GET("{month}/{day}/events.json")
    suspend fun getWikipediaData(@Path("day") day: Int, @Path("month") month: Int): Response<WikiEventResponse>
}