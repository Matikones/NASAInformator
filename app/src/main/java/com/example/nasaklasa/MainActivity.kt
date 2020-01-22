package com.example.nasaklasa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.nasaklasa.ui.main.Earth
import com.example.nasaklasa.ui.main.MainFragment
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_fragment.*
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val fragment = MainFragment()
        supportFragmentManager.beginTransaction().apply {
            add(R.id.container, fragment)
            commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_details, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.act0){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment())
                .commitNow()
        }
        if (item.itemId == R.id.act1){
            supportFragmentManager.beginTransaction()
            .replace(R.id.container, Earth())
            .commitNow()
        }
        if (item.itemId == R.id.act2){
            Toast.makeText(this, "funkcja Mateusza ", Toast.LENGTH_LONG).show()
        }
        if (item.itemId == R.id.act3){
            Toast.makeText(this, "funkcja Filipa", Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }
}
