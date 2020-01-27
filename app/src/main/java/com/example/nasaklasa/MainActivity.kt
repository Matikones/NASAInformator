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
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
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
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)
        updateUI()
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.naw_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
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

            val mainViewModel = MainViewModel()
            val recyclerView = findViewById<RecyclerView>(R.id.recyclerView22446)
            mainViewModel.recycler(this, recyclerView)

        }
        drawerLayout.closeDrawer(GravityCompat.START)
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
