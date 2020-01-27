package com.example.nasaklasa.ui.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProviders

import com.example.nasaklasa.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener



class TVFragment : Fragment() {
    lateinit var mainViewModel: MainViewModel
    lateinit var youTubePlayerView: YouTubePlayerView

    lateinit var progressBar: ProgressBar
    lateinit var container: FrameLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainViewModel = activity!!.run{
            ViewModelProviders.of(this).get(MainViewModel::class.java)
        }

        youTubePlayerView = view!!.findViewById(R.id.youtube_player_view)

        progressBar = activity!!.findViewById(R.id.progressBar)
        container = activity!!.findViewById(R.id.container)
        container.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
        initYouTubePlayerView()
    }
    private fun initYouTubePlayerView() {
        lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = "21X5lGlDOfg"
                youTubePlayer.loadVideo(videoId, 0f)

                progressBar.visibility = View.GONE
                container.visibility = View.VISIBLE
            }
        })
    }
}
