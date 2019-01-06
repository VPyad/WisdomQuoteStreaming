package com.example.vpyad.wisdomquotestreaming

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vpyad.wisdomquotestreaming.Adapters.StreamQuoteRecyclerAdapter
import com.example.vpyad.wisdomquotestreaming.Models.Quote
import kotlinx.android.synthetic.main.fragment_quote_stream.*

class QuoteStreamFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quote_stream, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var quotes = getQuotes()

        val context: Context = this.context ?: return

        streamRecyclerView.layoutManager = LinearLayoutManager(context)
        streamRecyclerView.adapter = StreamQuoteRecyclerAdapter(quotes, context)
        super.onViewCreated(view, savedInstanceState)
    }

    private fun getQuotes(): ArrayList<Quote>{
        val quotes: ArrayList<Quote> = ArrayList()

        quotes.add(Quote("The only people who never fail are those who never try.","Ilka Chase",""))
        quotes.add(Quote("Failure is just another way to learn how to do something right.","Marian Wright Edelman",""))
        quotes.add(Quote("I failed my way to success.","Thomas Edison",""))
        quotes.add(Quote("Every failure brings with it the seed of an equivalent success.","Napoleon Hill",""))
        quotes.add(Quote("Every failure brings with it the seed of an equivalent success.","Napoleon Hill",""))

        return quotes
    }
}
