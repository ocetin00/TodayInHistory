package com.oguzhancetin.todayinhistory.model

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("description")
    val description: String = "",
    @SerializedName("wikipedia")
    val wikipedia: List<Wikipedia> = emptyList(),
    @SerializedName("year")
    val year: String = ""
)