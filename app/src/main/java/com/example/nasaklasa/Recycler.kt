package com.example.nasaklasa

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.CursorWrapper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nasaklasa.ui.main.MainViewModel
import com.example.nasaklasa.ui.main.Save
import kotlinx.android.synthetic.main.fragment_earth.*
import kotlinx.android.synthetic.main.save_row.view.*


class UserListAdapter(var users: MutableList<SaveFormat>, var  context: Context, var recyclerView: RecyclerView): RecyclerView.Adapter<UserListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.save_row,parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var user = users[position]
        holder.userIDText.text = user.ID.toString()
        holder.userTitleText.text = user.Title
        holder.userDateText.text = user.Date
        holder.userDescText.text = user.Desc
        holder.userOtherText.text = user.Other
        holder.userUrlText.text = user.Url
        Glide.with(context)
            .load(user.Url)
            .fitCenter()
            .placeholder(R.mipmap.ic_nasa_foreground)
            .into(holder.userImage)

        holder.itemView.zapisz_row.setOnCheckedChangeListener{zapisz_row, isChecked->
            if (zapisz_row.isChecked){
                Log.e("last","działa")
            }else{
                Log.e("last"," niedziała")
                val mainViewModel = MainViewModel()
                mainViewModel.unsave(context, user.ID.toString(), true)

                mainViewModel.recycler(context, recyclerView) // do odświerzenia recyclera
            }
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var userIDText = itemView.findViewById<TextView>(R.id.Id_row)
        var userTitleText = itemView.findViewById<TextView>(R.id.Title_row)
        var userDateText = itemView.findViewById<TextView>(R.id.Date_row)
        var userDescText = itemView.findViewById<TextView>(R.id.Desc_row)
        var userOtherText = itemView.findViewById<TextView>(R.id.Other_row)
        var userUrlText = itemView.findViewById<TextView>(R.id.Url_row)
        var userImage = itemView.findViewById<ImageView>(R.id.ivImageMain_row)
        var userCheckbox = itemView.findViewById<CheckBox>(R.id.zapisz_row)
    }

}

class UserCursorWrapper(cursor: Cursor?): CursorWrapper(cursor) {
    fun getSaveFormat(): SaveFormat{
        var id = getInt(getColumnIndex(Db_Helper.COL_ID))
        var title = getString(getColumnIndex(Db_Helper.COL_TITLE))
        var date = getString(getColumnIndex(Db_Helper.COL_DATE))
        var desc = getString(getColumnIndex(Db_Helper.COL_DESC))
        var other = getString(getColumnIndex(Db_Helper.COL_OTHER))
        var url = getString(getColumnIndex(Db_Helper.COL_URL))

        var saveFormat = SaveFormat(id, title, date, desc, other, url)
        return saveFormat
    }
}