package com.example.vpyad.wisdomquotestreaming

import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val manager = supportFragmentManager

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_single_quote -> {
                val singleQuoteFragment = SingleQuoteFragment()
                replaceFragment(singleQuoteFragment)

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_live -> {
                val streamQuoteFragment = QuoteStreamFragment()
                replaceFragment(streamQuoteFragment)

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation)
        bottomNavigationView.selectedItemId = R.id.navigation_single_quote
    }

    private fun replaceFragment(fragment: android.support.v4.app.Fragment){
        val transaction = manager.beginTransaction()

        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)

        transaction.commit()
    }
}
