package com.example.nasaklasa

import android.content.Context
import android.database.Cursor
import android.database.CursorWrapper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaklasa.ui.main.Earth
import com.example.nasaklasa.ui.main.MainFragment
import com.example.nasaklasa.ui.main.MainViewModel
import com.example.nasaklasa.ui.main.Save

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
}

