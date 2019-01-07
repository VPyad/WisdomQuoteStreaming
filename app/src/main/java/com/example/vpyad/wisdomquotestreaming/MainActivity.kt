package com.example.vpyad.wisdomquotestreaming

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val manager = supportFragmentManager
    private  val singleQuoteFragment = SingleQuoteFragment()
    private val streamQuoteFragment = QuoteStreamFragment()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_single_quote -> {
                swithcFragment(singleQuoteFragment, streamQuoteFragment)

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_live -> {
                swithcFragment(streamQuoteFragment, singleQuoteFragment)

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        supportActionBar?.setTitle(R.string.toolBarName)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation)
        bottomNavigationView.selectedItemId = R.id.navigation_single_quote
    }

    private fun swithcFragment(fragmentShow: android.support.v4.app.Fragment, fragmentHide: Fragment) {
        val transaction = manager.beginTransaction()

        if (fragmentShow.isAdded) {
            transaction.show(fragmentShow)
        }
        else {
            transaction.add(R.id.fragment_container, fragmentShow)
        }

        if (fragmentHide.isAdded) {
            transaction.hide(fragmentHide)
        }

        transaction.commit()
    }
}
