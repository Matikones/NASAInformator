package com.example.nasaklasa

import android.content.Context
import android.database.Cursor
import android.database.CursorWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

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
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var userIDText = itemView.findViewById<TextView>(R.id.Id_row)
        var userTitleText = itemView.findViewById<TextView>(R.id.Title_row)
        var userDateText = itemView.findViewById<TextView>(R.id.Date_row)
        var userDescText = itemView.findViewById<TextView>(R.id.Desc_row)
        var userOtherText = itemView.findViewById<TextView>(R.id.Other_row)
        var userUrlText = itemView.findViewById<TextView>(R.id.Url_row)
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