package com.example.nasaklasa.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasaklasa.JsonPlaceHolderApi
import com.example.nasaklasa.Model
import com.example.nasaklasa.Post
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {

    val model = Model()

    var mutableLiveData = MutableLiveData<List<String>>()

    fun getStringMain(): LiveData<List<String>> {
        mutableLiveData = model.mainFragmentData()
        return mutableLiveData
    }

    var mutableLiveData2 = MutableLiveData<List<String>>()

    fun getStringEarth(): LiveData<List<String>> {
        mutableLiveData2 = model.earthFragmentData()
        return mutableLiveData2
    }
}
