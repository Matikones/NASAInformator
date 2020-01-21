package com.example.nasaklasa

import retrofit2.Call
import retrofit2.http.GET

interface JsonPlaceHolderApi {
    @GET("apod?api_key=1TZqakCoBxOHcNJXovfFPxk1zxyWaZwffFIOpt4V")
    open fun getPosts(): Call<Post>
}