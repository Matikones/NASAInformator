package com.example.nasaklasa.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.nasaklasa.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    lateinit var mainViewModel : MainViewModel

    private lateinit var viewModel: MainViewModel

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

        mainViewModel.getString().observe(this, object : Observer<List<String>> {
            override fun onChanged(t: List<String>) {
                Title.text = t[0]
                Date.text = t[1]
                Url.text = t[2]
                Explanation.text = t[3]
            }
        })
    }

}
