package com.example.nasaklasa

import android.content.Context
import android.util.Log
import androidx.core.content.contentValuesOf
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Model {

    var recyclerView123: RecyclerView? = null


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
        val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApiEarth::class.java)
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

    fun marsFragmentData(): MutableLiveData<List<String>>{

        val mutableLiveData = MutableLiveData<List<String>>()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApiMars::class.java)

        val call: retrofit2.Call<PostMars> = jsonPlaceHolderApi.getPosts()

        call!!.enqueue(
            object : Callback<PostMars> {
                override fun onResponse(
                    call: retrofit2.Call<PostMars>,
                    response: Response<PostMars>
                ) {
                    if (!response.isSuccessful) {
                        return
                    }
                    val posts = response.body()!!
                    var content = ""
                    var list = mutableListOf<String>()
                    //list.add(posts.getPhoto()[0].img_src)
                    //list.add(posts.getPhoto()[1].img_src)
                    //list.add(posts.getPhoto()[2].img_src)
                    //list.add(posts.getPhoto()[3].img_src)
                    //list.add(posts.getPhoto()[4].img_src)
                    //list.add(posts.getPhoto()[5].img_src)
                    //list.add(posts.getPhoto()[6].img_src)
                    //list.add(posts.getPhoto()[7].img_src)
                    content += "\n img: \n" + posts.getPhoto()[0].img_src + "\n"
                    content += "\n img: \n" + posts.getPhoto()[1].img_src + "\n"
                    content += "\n img: \n" + posts.getPhoto()[2].img_src + "\n"
                    content += "\n img: \n" + posts.getPhoto()[3].img_src + "\n"
                    content += "\n img: \n" + posts.getPhoto()[4].img_src + "\n"
                    content += "\n img: \n" + posts.getPhoto()[5].img_src + "\n"
                    content += "\n img: \n" + posts.getPhoto()[6].img_src + "\n"
                    content += "\n img: \n" + posts.getPhoto()[7].img_src + "\n"
                    Log.e("jakto", content)
                    for(i in 0..7){
                        list.add(content)}
                    mutableLiveData.value = list
                }

                override fun onFailure(
                    call: retrofit2.Call<PostMars>,
                    t: Throwable
                ) { }

            })
        return mutableLiveData
    }



    fun save(context: Context, title: String, date: String, desc: String, other: String, url: String): Int{
        var db = Db_Helper(context)
        val newSaveFormat = SaveFormat(null, title, date, desc, other, url)
        db.insertData(newSaveFormat)

        var savedata = mutableListOf<SaveFormat>()
        savedata = db.getAllSaveFormat()

        Log.e("czydziała",savedata.count().toString())
        return savedata.count()
    }

    fun unsave(context: Context, id: String, bol: Boolean): MutableList<SaveFormat>{
        var users = mutableListOf<SaveFormat>()
        var db = Db_Helper(context)
        users = db.getAllSaveFormat()
        var cosw: Int = 0
        users.forEach{
            var cos = it.ID.toString()
            var cos2 = cos.toInt()
            if (cos2 > cosw){
                cosw = cos2
            }
        }
        Log.e("usun", cosw.toString())
        if (bol){
            cosw = id.toInt()
        }
        db.deleteUser(cosw)
        return users
    }

    fun recycler(context: Context, recyclerView: RecyclerView){
        recyclerView123 = recyclerView
        var users = mutableListOf<SaveFormat>()
        var db = Db_Helper(context)
        users = db.getAllSaveFormat()
        recyclerView.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        recyclerView.adapter = UserListAdapter(users,context,recyclerView)
    }
}