package com.example.vpyad.wisdomquotestreaming.Models

import org.json.JSONObject

data class Quote (val quote: String, val author: String, val img: String) {

    companion object {
        fun parse(jsonStr: String): Quote {
            val json = JSONObject(jsonStr)

            val text = json.getString("quote")
            val author = json.getString("author")
            val img = json.getString("img")

            return Quote(text, author, img)
        }
    }
}