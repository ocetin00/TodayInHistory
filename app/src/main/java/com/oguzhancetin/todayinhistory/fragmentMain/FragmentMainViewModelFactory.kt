package com.oguzhancetin.todayinhistory.fragmentMain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oguzhancetin.todayinhistory.data.WikiRepository

class FragmentMainViewModelFactory(private val wikiRepository: WikiRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FragmentMainViewModel::class.java)){
            return FragmentMainViewModel(wikiRepository) as T
        }
        throw IllegalArgumentException("umsported class")
    }
}