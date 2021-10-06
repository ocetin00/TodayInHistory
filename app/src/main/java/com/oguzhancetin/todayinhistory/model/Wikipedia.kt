package com.oguzhancetin.todayinhistory.model

import com.google.gson.annotations.SerializedName

data class Wikipedia(
    @SerializedName("title")
    val title: String = "",
    @SerializedName("wikipedia")
    val wikipedia: String = ""
)