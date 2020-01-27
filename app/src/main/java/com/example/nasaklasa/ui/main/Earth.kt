package com.example.nasaklasa.ui.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide

import com.example.nasaklasa.R
import kotlinx.android.synthetic.main.fragment_earth.*

class Earth : Fragment() {

    lateinit var mainViewModel : MainViewModel
    private lateinit var viewModel: MainViewModel

    lateinit var progressBar: ProgressBar
    lateinit var container: FrameLayout

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
        progressBar = activity!!.findViewById(R.id.progressBar)
        container = activity!!.findViewById(R.id.container)
        container.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE

        mainViewModel.getStringEarth().observe(this, object : Observer<List<String>> {
            override fun onChanged(t: List<String>) {
                Date_row.text = t[1]
                Cloud_score.text = t[0]
                Url_row.text = t[4]
                var imageUrl = t[4]

                Glide.with(this@Earth)
                    .load(imageUrl)
                    .fitCenter()
                    .placeholder(R.mipmap.ic_nasa_foreground)
                    .into(ivImage)

                progressBar.visibility = View.GONE
                container.visibility = View.VISIBLE
            }
        })
//------------------------------------------------------------------------------

        Zapisz.setOnCheckedChangeListener{Zapisz, isChecked ->
            if (Zapisz.isChecked){
                mainViewModel.save(this.requireContext(), Title.text.toString(), Date_row.text.toString(), Cloud_score.text.toString(), "", Url_row.text.toString())
                Toast.makeText(context, "Zapisano", Toast.LENGTH_LONG).show()
            }
            else{
                mainViewModel.unsave(this.requireContext(), "0", false)
                Toast.makeText(context, "Usunięto", Toast.LENGTH_LONG).show()
            }

        }
    }

}
