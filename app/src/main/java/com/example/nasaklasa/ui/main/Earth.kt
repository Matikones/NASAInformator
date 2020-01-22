package com.example.nasaklasa.ui.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide

import com.example.nasaklasa.R
import kotlinx.android.synthetic.main.fragment_earth.*

class Earth : Fragment() {

    lateinit var mainViewModel : MainViewModel

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_earth, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        mainViewModel = activity!!.run {
            ViewModelProviders.of(this).get(MainViewModel::class.java)
        }

        mainViewModel.getStringEarth().observe(this, object : Observer<List<String>> {
            override fun onChanged(t: List<String>) {
                Id.text = t[2]
                Date.text = t[1]
                Cloud_score.text = t[0]
                Url.text = t[4]
                var imageUrl = t[4]

                Glide.with(this@Earth)
                    .load(imageUrl)
                    .fitCenter()
                    .placeholder(R.mipmap.ic_nasa_foreground)
                    .into(ivImage)
            }
        })
    }

}
