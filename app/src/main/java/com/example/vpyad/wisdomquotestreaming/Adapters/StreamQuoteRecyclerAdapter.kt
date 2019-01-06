package com.example.vpyad.wisdomquotestreaming.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.vpyad.wisdomquotestreaming.R
import com.example.vpyad.wisdomquotestreaming.CustomViews.StreamQuoteCell
import com.example.vpyad.wisdomquotestreaming.Models.Quote

class StreamQuoteRecyclerAdapter(private val quotes: ArrayList<Quote>, private val context: Context) : RecyclerView.Adapter<StreamQuoteCell>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StreamQuoteCell {
        return StreamQuoteCell(LayoutInflater.from(context).inflate(R.layout.stream_quote_item, parent, false))
    }

    override fun getItemCount(): Int {
        return quotes.count()
    }

    override fun onBindViewHolder(holder: StreamQuoteCell, position: Int) {
        val quote = quotes[position]
        holder.bindQuote(quote)
    }
}