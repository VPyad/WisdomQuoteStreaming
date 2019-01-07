package com.example.vpyad.wisdomquotestreaming.Services

import com.example.vpyad.wisdomquotestreaming.Models.Quote
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface QuoteApiService {
    companion object {
        const val HTTP_BASE_URL = "http://192.168.1.244:1337/"
        const val QUOTE_ENDPOINT = "quote"
        const val STREAM_ENDPOINT = "live"
        const val WS_BASE_URL ="ws://192.168.1.244:1337/"

        fun create(): QuoteApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(HTTP_BASE_URL)
                .build()

            return retrofit.create(QuoteApiService::class.java)
        }
    }

    @GET(QuoteApiService.QUOTE_ENDPOINT)
    fun getQuote() : Observable<Quote>
}