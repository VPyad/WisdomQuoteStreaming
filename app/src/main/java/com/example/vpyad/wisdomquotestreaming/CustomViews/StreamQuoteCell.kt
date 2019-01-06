package com.example.vpyad.wisdomquotestreaming.CustomViews

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.vpyad.wisdomquotestreaming.Models.Quote
import kotlinx.android.synthetic.main.stream_quote_item.view.*

class StreamQuoteCell(v: View) : RecyclerView.ViewHolder(v) {
    private var view: View = v
    private var quote: Quote? = null

    fun bindQuote(quote: Quote){
        this.quote = quote

        view.quoteTextView.text = quote.text
        view.authorTextView.text = quote.author
    }
}