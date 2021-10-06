package com.oguzhancetin.todayinhistory.model

import com.google.gson.annotations.SerializedName

data class WikiEventResponse(
    @SerializedName("date")
    val date: String = "",
    @SerializedName("events")
    val events: List<Event> = emptyList(),
    @SerializedName("wikipedia")
    val wikipedia: String = ""
)