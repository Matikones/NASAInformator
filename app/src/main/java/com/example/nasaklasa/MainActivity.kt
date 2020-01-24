package com.example.nasaklasa

import android.content.Context
import android.database.Cursor
import android.database.CursorWrapper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaklasa.ui.main.Earth
import com.example.nasaklasa.ui.main.MainFragment
import com.example.nasaklasa.ui.main.TVFragment
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_fragment.*
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.nasaklasa.ui.main.MainViewModel
import com.example.nasaklasa.ui.main.Save

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        updateUI()
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
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TVFragment())
                .commitNow()
        }
        if (item.itemId == R.id.act4){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, Save())
                .commitNow()

            var mainViewModel = MainViewModel()
            var recyclerView = findViewById<RecyclerView>(R.id.recyclerView22446)
            mainViewModel.recycler(this, recyclerView)

        }
        return super.onOptionsItemSelected(item)
    }
    private fun updateUI(){
        val fm = supportFragmentManager
        var fragment = fm.findFragmentById(R.id.container)
        if(fragment == null){
            fragment = MainFragment()
            fm.beginTransaction().add(R.id.container, fragment).commit()
        }
        else if(fragment is Earth){
            fragment = Earth()
            fm.beginTransaction().replace(R.id.container, fragment).commit()
        }
//        else if(fragment is nazwaklasyfragmentuMateusza){
//            fragment = nazwaklasyfragmentuMateusza()
//            fm.beginTransaction().replace(R.id.container, fragment).commit()
//        }
        else if(fragment is TVFragment){
            fragment = TVFragment()
            fm.beginTransaction().replace(R.id.container, fragment).commit()
        }
        else if(fragment is Save){
            fragment = Save()
            fm.beginTransaction().replace(R.id.container, fragment).commit()
        }
        else{
            Log.w("fragment", "Niezdefiniowany fragment do wyswietlenia")
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment())
                .commitNow()
        }
    }
}
