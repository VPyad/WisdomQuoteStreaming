package com.example.vpyad.wisdomquotestreaming

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vpyad.wisdomquotestreaming.Models.Quote
import kotlinx.android.synthetic.main.fragment_single_quote.*

class SingleQuoteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_single_quote, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setQuote()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setQuote(){
        val text = "Failure is just another way to learn how to do something right."
        val author = "Marian Wright Edelman"
        val quote = Quote(text, author, "")

        singleQuoteView.setQuote(quote)
    }
}
