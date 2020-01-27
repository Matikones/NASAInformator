package com.example.nasaklasa

import com.google.gson.annotations.SerializedName

class PostMars {
    private val photos = listOf<inner1>()

    @SerializedName("body")

    open

    fun getPhoto(): List<inner1> {
        return photos
    }

}
class inner1 {
    val id_photos: String = ""
    val img_src: String = ""
}