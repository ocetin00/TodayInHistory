package com.oguzhancetin.todayinhistory.data

import android.util.Log
import com.oguzhancetin.todayinhistory.model.WikiResult
import com.oguzhancetin.todayinhistory.network.service.WikiApi
import com.oguzhancetin.todayinhistory.network.service.RetrofitInstance
import com.oguzhancetin.todayinhistory.model.WikiEventResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WikiRepository() {

    private val TAG = "WikiRepository"
     fun getWikiData(day: Int, month: Int): Flow<WikiResult<WikiEventResponse?>> = flow {

        emit(WikiResult.Loading<WikiEventResponse?>())
        try {
            val result = RetrofitInstance.retrofitInstance.getWikipediaData(day, month)
            Log.d(TAG, "getWikiData: ${result.code()}  ----   ${result.message()} --- ${result.raw()} ")
            emit(WikiResult.Succes<WikiEventResponse?>(result.body()))
        } catch (e: Exception) {
            emit(WikiResult.Error<WikiEventResponse?>(e.message))
        }

    }.flowOn(Dispatchers.IO)

}