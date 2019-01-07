package com.example.vpyad.wisdomquotestreaming.CustomViews

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.vpyad.wisdomquotestreaming.Models.Quote
import com.example.vpyad.wisdomquotestreaming.Services.QuoteApiService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.stream_quote_item.view.*

class StreamQuoteCell(v: View) : RecyclerView.ViewHolder(v) {
    private var view: View = v
    private var quote: Quote? = null

    fun bindQuote(quote: Quote){
        this.quote = quote

        view.quoteTextView.text = quote.quote
        view.authorTextView.text = quote.author
        Picasso.get().load(QuoteApiService.HTTP_BASE_URL + quote.img.removeRange(0, 1)).fit().into(view.quoteImageView)
    }
}