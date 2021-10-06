package com.oguzhancetin.todayinhistory.model

sealed class WikiResult<T> (
    val data:T? = null,
    val message:String? = null
        ){
     class Succes<T>(data:T): WikiResult<T>(data)
     class Error<T>(message: String?): WikiResult<T>(message = message)
     class Loading<T>: WikiResult<T>()
}