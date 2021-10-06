package com.oguzhancetin.todayinhistory.fragmentMain

import android.os.Build
import androidx.lifecycle.*
import com.oguzhancetin.todayinhistory.data.WikiRepository
import com.oguzhancetin.todayinhistory.model.WikiEventResponse
import com.oguzhancetin.todayinhistory.model.WikiResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class FragmentMainViewModel(private val repository: WikiRepository) : ViewModel() {


    private val _events = MutableLiveData<WikiResult<WikiEventResponse?>>()
    val events: LiveData<WikiResult<WikiEventResponse?>>
        get() = _events


    private var dayOfMonth: Int = 0
    private var month: Int = 0


    init {
        initializeDates()
        getWikiData(dayOfMonth, month)
    }

    private fun initializeDates() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val currentDate: LocalDate = LocalDate.now()
            dayOfMonth = currentDate.dayOfMonth
            month = currentDate.monthValue
        } else {
            val calender = Calendar.getInstance()
            dayOfMonth = calender.get(Calendar.DAY_OF_MONTH)
            month = calender.get(Calendar.MONTH) + 1
        }
    }

    private fun getWikiData(day: Int, month: Int) {
        viewModelScope.launch {
            repository.getWikiData(day, month).collect {
                _events.value = it
            }

        }

    }
    public fun getDateDesc() = events.value

}