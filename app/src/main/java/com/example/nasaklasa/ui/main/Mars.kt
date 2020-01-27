package com.example.nasaklasa.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.nasaklasa.R
import kotlinx.android.synthetic.main.fragment_mars.*
import kotlinx.android.synthetic.main.main_fragment.*

class Mars : Fragment() {

    lateinit var mainViewModel : MainViewModel

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_mars, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        mainViewModel = activity!!.run {
            ViewModelProviders.of(this).get(MainViewModel::class.java)
        }

        mainViewModel.getStringMars().observe(this, object : Observer<List<String>> {
            override fun onChanged(t: List<String>) {
                val imageUrl1 = t[0]
                val imageUrl2 = t[1]

                Glide.with(this@Mars)
                    .load(imageUrl1)
                    .fitCenter()
                    .placeholder(R.mipmap.ic_nasa_foreground)
                    .into(ivImage1)

                Glide.with(this@Mars)
                    .load(imageUrl2)
                    .fitCenter()
                    .placeholder(R.mipmap.ic_nasa_foreground)
                    .into(ivImage2)
            }
        })
    }

}