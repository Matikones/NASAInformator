package com.example.nasaklasa

import com.google.gson.annotations.SerializedName

class PostEarth {
    private val cloud_score = ""
    private val date = ""
    private val id = ""
    private val service_version = ""
    private val url = ""

    @SerializedName("body")

    open

    fun getCloud_score(): String {
        return cloud_score
    }

    fun getDate(): String {
        return date
    }

    fun getId(): String? {
        return id
    }

    fun getService_version(): String? {
        return service_version
    }

    fun getUrl(): String? {
        return url
    }
}