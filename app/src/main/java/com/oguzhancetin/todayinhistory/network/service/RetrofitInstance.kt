package com.oguzhancetin.todayinhistory.network.service

import com.oguzhancetin.todayinhistory.util.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class RetrofitInstance {
    companion object{

        private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constant.BASE_URL)
            .build()

        val retrofitInstance: WikiApi by lazy {
            retrofit.create(WikiApi::class.java)
        }
    }
}