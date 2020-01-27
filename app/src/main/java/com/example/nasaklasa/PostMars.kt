package com.example.nasaklasa

import com.google.gson.annotations.SerializedName

class PostMars {
    private val url = ""
    @SerializedName("body")

    open
    fun getUrl(): String? {
        return url
    }
}