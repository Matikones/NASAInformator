package com.example.nasaklasa

import androidx.lifecycle.MutableLiveData
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Model {

    fun mainFragmentData(): MutableLiveData<List<String>>{

        val mutableLiveData = MutableLiveData<List<String>>()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/planetary/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val jsonPlaceHolderApi = retrofit.create(
            JsonPlaceHolderApi::class.java
        )
        val call: retrofit2.Call<Post> = jsonPlaceHolderApi.getPosts()

        call!!.enqueue(
            object : Callback<Post> {
                override fun onResponse(
                    call: retrofit2.Call<Post>,
                    response: Response<Post>
                ) {
                    if (!response.isSuccessful) {
                        return
                    }
                    val posts = response.body()!!
                    var list = mutableListOf<String>()
                    list.add(posts.getTitle())
                    list.add(posts.getDate())
                    list.add(posts.getUrl().toString())
                    list.add(posts.getExplanation().toString())
                    mutableLiveData.value = list
                }

                override fun onFailure(
                    call: retrofit2.Call<Post>,
                    t: Throwable
                ) { }
            })
        return mutableLiveData
    }

    fun earthFragmentData(): MutableLiveData<List<String>>{

        val mutableLiveData = MutableLiveData<List<String>>()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/planetary/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val jsonPlaceHolderApi = retrofit.create(
            JsonPlaceHolderApiEarth::class.java
        )
        val call: retrofit2.Call<PostEarth> = jsonPlaceHolderApi.getPosts()

        call!!.enqueue(
            object : Callback<PostEarth> {
                override fun onResponse(
                    call: retrofit2.Call<PostEarth>,
                    response: Response<PostEarth>
                ) {
                    if (!response.isSuccessful) {
                        return
                    }
                    val posts = response.body()!!
                    var list = mutableListOf<String>()
                    list.add(posts.getCloud_score())
                    list.add(posts.getDate())
                    list.add(posts.getId().toString())
                    list.add(posts.getService_version().toString())
                    list.add(posts.getUrl().toString())
                    mutableLiveData.value = list
                }

                override fun onFailure(
                    call: retrofit2.Call<PostEarth>,
                    t: Throwable
                ) { }
            })
        return mutableLiveData
    }
}