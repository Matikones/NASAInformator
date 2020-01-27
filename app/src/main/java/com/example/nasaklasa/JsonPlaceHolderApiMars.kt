package com.example.nasaklasa

import retrofit2.Call
import retrofit2.http.GET

interface JsonPlaceHolderApiMars {
    @GET("api/v1/rovers/curiosity/photos?earth_date=2015-6-3&api_key=1TZqakCoBxOHcNJXovfFPxk1zxyWaZwffFIOpt4V")

    open fun getPosts(): Call<List<PostMars>>
}