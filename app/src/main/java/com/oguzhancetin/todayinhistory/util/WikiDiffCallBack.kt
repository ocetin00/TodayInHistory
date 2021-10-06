package com.oguzhancetin.todayinhistory.util

import androidx.recyclerview.widget.DiffUtil
import com.oguzhancetin.todayinhistory.model.Event

class WikiDiffCallBack (
    private val old: List<Event>,
    private val new: List<Event>
    ) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return old.size
    }

    override fun getNewListSize(): Int {
        return new.size
    }

    override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return old[oldPosition].description.equals( new[newPosition].description)
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return old[oldPosition].description.equals( new[newPosition].description)
    }


    }