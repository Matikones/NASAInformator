package com.example.nasaklasa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_image.*
import kotlinx.android.synthetic.main.main_fragment.*

class ImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        val imageUrl: String = intent.getStringExtra("html")

        Glide.with(this@ImageActivity)
            .load(imageUrl)
            .fitCenter()
            .placeholder(R.mipmap.ic_nasa_foreground)
            .into(imageView)
    }
}
