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
    private var quotesList: ArrayList<Quote> = ArrayList()
    private val webSocket = RxWebsocket.Builder()
        .build(QuoteApiService.WS_BASE_URL + QuoteApiService.STREAM_ENDPOINT)

    private lateinit var streamQuoteRecyclerAdapter: StreamQuoteRecyclerAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quote_stream, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        connectToSocket()

        val context: Context = this.context ?: return

        streamQuoteRecyclerAdapter = StreamQuoteRecyclerAdapter(quotesList, context)
        linearLayoutManager = LinearLayoutManager(context)

        streamRecyclerView.layoutManager = linearLayoutManager
        streamRecyclerView.adapter = streamQuoteRecyclerAdapter

        upFAB.setOnClickListener(upButtonClickListener)

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        disconnectFromSocket()
        super.onDestroyView()
    }

    private val upButtonClickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.upFAB -> {
                linearLayoutManager.scrollToPosition(0)
            }
        }
    }

    private fun connectToSocket() {
        webSocket.connect()
            .flatMapPublisher { open -> open.client().listen() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { onSuccess ->
                    //Log.d("WS_DATA", onSuccess.data())

                    if (onSuccess.data() != null) {
                        val quote = Quote.parse(onSuccess.data()!!)

                        quotesList.add(0, quote)
                        streamQuoteRecyclerAdapter.notifyItemInserted(0)

                        if (quotesList.size >= 3) {
                            upFAB.visibility = View.VISIBLE
                        }
                    }
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
