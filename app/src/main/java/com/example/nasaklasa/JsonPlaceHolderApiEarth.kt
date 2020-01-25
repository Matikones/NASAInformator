package com.example.nasaklasa

import retrofit2.Call
import retrofit2.http.GET

interface JsonPlaceHolderApiEarth {
    @GET("earth/imagery/?lon=25.45&lat=5.5&date=2014-02-01&cloud_score=True&api_key=1TZqakCoBxOHcNJXovfFPxk1zxyWaZwffFIOpt4V")

    open fun getPosts(): Call<PostEarth>
}