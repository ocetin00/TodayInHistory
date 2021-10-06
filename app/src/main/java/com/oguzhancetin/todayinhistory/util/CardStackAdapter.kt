package com.oguzhancetin.todayinhistory.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oguzhancetin.todayinhistory.R
import com.oguzhancetin.todayinhistory.model.Event

class CardStackAdapter (
    private var events: List<Event> = emptyList()
    ) : RecyclerView.Adapter<CardStackAdapter.ViewHolder>()
    {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return ViewHolder(inflater.inflate(R.layout.item_event, parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val event = events[position]
            holder.descriptionText.text = "${event.description} "
            /**
            Glide.with(holder.image)
                .load(spot.url)
                .into(holder.image)
            holder.itemView.setOnClickListener { v ->
                Toast.makeText(v.context, spot.name, Toast.LENGTH_SHORT).show()
            }
            */
        }

        override fun getItemCount(): Int {
            return events.size
        }

        fun setEvents(events :List<Event>) {
            this.events = events
        }

        fun getEvents(): List<Event> {
            return events
        }

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val descriptionText: TextView = view.findViewById(R.id.text_event_desc)

        }

    }