package com.example.nasaklasa.ui.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaklasa.*
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {

    val model = Model()
    var mutableLiveData = MutableLiveData<List<String>>()
    var lastRecord: Int = 0

    fun getStringMain(): LiveData<List<String>> {
        mutableLiveData = model.mainFragmentData()
        return mutableLiveData
    }

    var mutableLiveData2 = MutableLiveData<List<String>>()

    fun getStringEarth(): LiveData<List<String>> {
        mutableLiveData2 = model.earthFragmentData()
        return mutableLiveData2
    }

    fun save(context: Context, title: String, date: String, desc: String, other: String, url: String){
        lastRecord = model.save(context, title, date, desc, other, url)
        Log.e("czydziała","działa - ")
    }

    fun unsave(context: Context){
        model.unsave(context, lastRecord)
        Log.e("czydziała","nie działa - ")
    }

    fun recycler(context: Context, recyclerView: RecyclerView){
        model.recycler(context, recyclerView)
    }
}
