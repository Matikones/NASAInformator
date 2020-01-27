package com.example.nasaklasa

import retrofit2.Call
import retrofit2.http.GET

interface JsonPlaceHolderApiMars {
    @GET("mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=1TZqakCoBxOHcNJXovfFPxk1zxyWaZwffFIOpt4V")
    open fun getPosts(): Call<PostMars>
}