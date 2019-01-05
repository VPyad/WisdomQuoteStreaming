package com.example.vpyad.wisdomquotestreaming.CustomViews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.vpyad.wisdomquotestreaming.R
import com.example.vpyad.wisdomquotestreaming.Models.Quote

class SingleQuoteView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : RelativeLayout(context, attrs, defStyleAttr) {
    private var quoteTextView : TextView
    private var authorTextView : TextView

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.single_quote_layout, this, true)

        quoteTextView = view.findViewById(R.id.quoteTextView)
        authorTextView = view.findViewById(R.id.authorTextView)
    }

    fun setQuote(quote: Quote){
        quoteTextView.text = quote.text
        authorTextView.text = quote.author
    }
}