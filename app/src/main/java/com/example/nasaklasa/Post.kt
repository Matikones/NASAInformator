package com.example.nasaklasa

import com.google.gson.annotations.SerializedName

class Post {
    private val date = ""
    private val explanation = ""
    private val media_type = ""
    private val service_version = ""
    private val title = ""
    private val url = ""

    @SerializedName("body")

    open

    fun getDate(): String {
        return date
    }

    fun getExplanation(): String? {
        return explanation
    }

    fun getMedia_type(): String {
        return media_type
    }

    fun getService_version(): String? {
        return service_version
    }

    fun getTitle(): String {
        return title
    }

    fun getUrl(): String? {
        return url
    }
}