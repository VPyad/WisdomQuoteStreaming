package com.example.vpyad.wisdomquotestreaming

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vpyad.wisdomquotestreaming.Adapters.StreamQuoteRecyclerAdapter
import com.example.vpyad.wisdomquotestreaming.Models.Quote
import com.example.vpyad.wisdomquotestreaming.Services.QuoteApiService
import com.navin.flintstones.rxwebsocket.RxWebsocket
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_quote_stream.*
import android.system.Os.listen
import android.widget.Toast


class QuoteStreamFragment() : Fragment() {
    private val webSocket = RxWebsocket.Builder()
        .build(QuoteApiService.WS_BASE_URL + QuoteApiService.STREAM_ENDPOINT)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quote_stream, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var quotes = getQuotes()

        connectToSocket()

        val context: Context = this.context ?: return

        streamRecyclerView.layoutManager = LinearLayoutManager(context)
        streamRecyclerView.adapter = StreamQuoteRecyclerAdapter(quotes, context)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        disconnectFromSocket()
        super.onDestroyView()
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

    private fun connectToSocket() {
        webSocket.connect()
            .flatMapPublisher { open -> open.client().listen() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { onSuccess ->
                    Log.d("WS_DATA", onSuccess.data())
                },
                { onError ->
                    showToast(R.string.onSocketConnectionErrorMessage)
                    Log.d("WS_CONN_ERROR", onError.localizedMessage)
                }
            )
    }

    private fun disconnectFromSocket() {
        webSocket.disconnect(0, "")
    }

    private fun showToast(resId: Int) {
        val context: Context = this.context ?: return
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show()
    }
}
