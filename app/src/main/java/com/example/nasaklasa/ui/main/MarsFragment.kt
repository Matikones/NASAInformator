package com.example.nasaklasa.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.nasaklasa.JsonPlaceHolderApiMars
import com.example.nasaklasa.PostMars

import com.example.nasaklasa.R
import kotlinx.android.synthetic.main.fragment_earth.*
import kotlinx.android.synthetic.main.fragment_earth.Date_row
import kotlinx.android.synthetic.main.fragment_earth.Url_row
import kotlinx.android.synthetic.main.fragment_mars.*
import kotlinx.android.synthetic.main.main_fragment.*
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.timer

class MarsFragment : Fragment() {

    lateinit var mainViewModel : MainViewModel
    private lateinit var viewModel: MainViewModel

    lateinit var progressBar: ProgressBar
    lateinit var container: FrameLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mars, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        mainViewModel = activity!!.run {
            ViewModelProviders.of(this).get(MainViewModel::class.java)
        }

        progressBar = activity!!.findViewById(R.id.progressBar)
        container = activity!!.findViewById(R.id.container)

        container.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
        mainViewModel.getStringMars().observe(this, object : Observer<List<String>> {
            override fun onChanged(t: List<String>?) {
                //var imageUrl1 = t[0]
                var imageUrl1 = t?.get(0)
                var imageUrl2 = t?.get(1)
                var imageUrl3 = t?.get(2)
                var imageUrl4 = t?.get(3)
                var imageUrl5 = t?.get(4)
                var imageUrl6 = t?.get(5)
                var imageUrl7 = t?.get(6)
                var imageUrl8 = t?.get(7)

                Log.e("cos", imageUrl1)
                Glide.with(this@MarsFragment)
                    .load(imageUrl1)
                    .fitCenter()
                    .placeholder(R.mipmap.ic_nasa_foreground)
                    .into(image1)

                Glide.with(this@MarsFragment)
                    .load(imageUrl2)
                    .fitCenter()
                    .placeholder(R.mipmap.ic_nasa_foreground)
                    .into(image2)

                Glide.with(this@MarsFragment)
                    .load(imageUrl3)
                    .fitCenter()
                    .placeholder(R.mipmap.ic_nasa_foreground)
                    .into(image3)

                Glide.with(this@MarsFragment)
                    .load(imageUrl4)
                    .fitCenter()
                    .placeholder(R.mipmap.ic_nasa_foreground)
                    .into(image4)

                Glide.with(this@MarsFragment)
                    .load(imageUrl5)
                    .fitCenter()
                    .placeholder(R.mipmap.ic_nasa_foreground)
                    .into(image5)

                Glide.with(this@MarsFragment)
                    .load(imageUrl6)
                    .fitCenter()
                    .placeholder(R.mipmap.ic_nasa_foreground)
                    .into(image6)

                Glide.with(this@MarsFragment)
                    .load(imageUrl7)
                    .fitCenter()
                    .placeholder(R.mipmap.ic_nasa_foreground)
                    .into(image7)

                Glide.with(this@MarsFragment)
                    .load(imageUrl8)
                    .fitCenter()
                    .placeholder(R.mipmap.ic_nasa_foreground)
                    .into(image8)

                progressBar.visibility = View.GONE
                container.visibility = View.VISIBLE
            }
        })



    }

}
