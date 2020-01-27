package com.example.nasaklasa.ui.main

import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.nasaklasa.R
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.main_fragment.Date_row
import kotlinx.android.synthetic.main.main_fragment.Url_row

class MainFragment : Fragment() {

    lateinit var mainViewModel : MainViewModel
    private lateinit var viewModel: MainViewModel

    lateinit var progressBar: ProgressBar
    lateinit var container: FrameLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
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
        mainViewModel.getStringMain().observe(this, object : Observer<List<String>> {
            override fun onChanged(t: List<String>) {
                Title_row.text = t[0]
                Date_row.text = t[1]
                Url_row.text = t[2]
                Desc_row.text = t[3]
                var imageUrl = t[2]

                Glide.with(this@MainFragment)
                    .load(imageUrl)
                    .fitCenter()
                    .placeholder(R.mipmap.ic_nasa_foreground)
                    .into(ivImageMain_row)

                //Zadanie 2
                Url_row.setOnClickListener{
                    Log.e("lolo","okoko")
                    val openURL = Intent(Intent.ACTION_VIEW)
                    openURL.data = Uri.parse(imageUrl)
                    startActivity(openURL)
                }
                //Koniec zadania 2
                progressBar.visibility = View.GONE
                container.visibility = View.VISIBLE
            }
        })
    }
}
