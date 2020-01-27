package com.example.nasaklasa.ui.main


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.nasaklasa.JsonPlaceHolderApiMars
import com.example.nasaklasa.PostMars

import com.example.nasaklasa.R
import kotlinx.android.synthetic.main.fragment_earth.*
import kotlinx.android.synthetic.main.fragment_mars.*
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.timer

/**
 * A simple [Fragment] subclass.
 */
class MarsFragment : Fragment() {

    lateinit var mainViewModel : MainViewModel
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mars, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
//
//        mainViewModel = activity!!.run {
//            ViewModelProviders.of(this).get(MainViewModel::class.java)
//        }
//        mainViewModel.getStringMars().observe(this, object : Observer<List<List<String>>> {
//            override fun onChanged(t: List<List<String>>) {
//                JSONstring.text = t[0][0]
//                Log.e("jakto", "okiidoki")
//
//            }
//        })

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
                        JSONstring.text = "code: " + response.code()
                        return
                    }
                    val posts = response.body()!!
                    var content = ""
                    content += "\n img: \n" + posts.getPhoto()[0].img_src + "\n"
                    content += "\n img: \n" + posts.getPhoto()[1].img_src + "\n"
                    content += "\n img: \n" + posts.getPhoto()[2].img_src + "\n"
                    content += "\n img: \n" + posts.getPhoto()[3].img_src + "\n"
                    content += "\n img: \n" + posts.getPhoto()[4].img_src + "\n"
                    content += "\n img: \n" + posts.getPhoto()[5].img_src + "\n"
                    content += "\n img: \n" + posts.getPhoto()[6].img_src + "\n"
                    content += "\n img: \n" + posts.getPhoto()[7].img_src + "\n"
                    JSONstring.append(content)
                    Log.e("jakto", content)
                }

                override fun onFailure(call: retrofit2.Call<PostMars>, t: Throwable) {
                    JSONstring.text = t.message
                }

            })
    }

}
