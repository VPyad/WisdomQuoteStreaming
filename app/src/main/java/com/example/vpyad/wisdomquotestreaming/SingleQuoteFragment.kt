package com.example.vpyad.wisdomquotestreaming

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.example.vpyad.wisdomquotestreaming.Models.Quote
import com.example.vpyad.wisdomquotestreaming.Services.QuoteApiService
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_single_quote.*

class SingleQuoteFragment : Fragment() {
    private val quoteApiService by lazy { QuoteApiService.create() }
    private var disposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_single_quote, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        nextButton.setOnClickListener(nextButtonClickListener)
        requestQuote()

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        disposable?.dispose()
        super.onDestroyView()
    }

    private val nextButtonClickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.nextButton -> {
                requestQuote()
            }
        }
    }

    private fun requestQuote() {
        showProgress()

        disposable = quoteApiService.getQuote()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    setQuote(result)
                    hideProgress()
                },
                { error ->
                    //showToast(R.string.onRequestErrorMessage)
                    showToast(error.message.toString())
                    hideProgress()
                }
            )
    }

    private fun setQuote(quote: Quote){
       singleQuoteView.setQuote(quote)
    }

    private fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    private fun showToast(text: String) {
        val context: Context = this.context ?: return
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    private fun showToast(resId: Int) {
        val context: Context = this.context ?: return
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show()
    }
}
