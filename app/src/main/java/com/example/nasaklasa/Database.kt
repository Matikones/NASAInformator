package com.example.nasaklasa

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.CursorWrapper
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Db_Helper(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Database.db"
        const val TABLE_NAME = "SaveArt"

        const val COL_ID = "id"
        const val COL_TITLE = "title"
        const val COL_DATE = "date"
        const val COL_DESC = "description"
        const val COL_OTHER = "other"
        const val COL_URL = "url"
    }
    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE " +
                TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TITLE + " VARCHAR(256), " +
                COL_DATE + " VARCHAR(256), " +
                COL_DESC + " VARCHAR(256), " +
                COL_OTHER + " VARCHAR(256), " +
                COL_URL + " VARCHAR(256))"
        db?.execSQL(createTable)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }
    fun insertData(saveFormat: SaveFormat){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_TITLE, saveFormat.Title)
        cv.put(COL_DATE, saveFormat.Date)
        cv.put(COL_DESC, saveFormat.Desc)
        cv.put(COL_OTHER, saveFormat.Other)
        cv.put(COL_URL, saveFormat.Url)
        db.insert(TABLE_NAME,null, cv)
        db.close()
    }
    private fun queryUsers(): UserCursorWrapper {
        val db = this.readableDatabase
        var rawQuery = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        return UserCursorWrapper(rawQuery)
    }
    fun getAllSaveFormat(): MutableList<SaveFormat>{
        var cursor = queryUsers()
        var saveFormat = mutableListOf<SaveFormat>()
        try{
            cursor.moveToFirst()
            while(!cursor.isAfterLast){
                saveFormat.add(cursor.getSaveFormat())
                cursor.moveToNext()
            }
        } catch(e: Exception){
            //
        }
        finally{
            cursor.close()
        }
        return saveFormat
    }
    fun updateSaveFormat(_id: Int, _title: String, _date: String, _desc: String, _other: String, _url: String){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_TITLE, _title)
        values.put(COL_DATE, _date)
        values.put(COL_DESC, _desc)
        values.put(COL_OTHER, _other)
        values.put(COL_URL, _url)
        val _success = db.update(TABLE_NAME, values, COL_ID + "=?", arrayOf(_id.toString())).toLong()
        db.close()
    }
    fun deleteUser(_id: Int): Boolean {
        val db = this.writableDatabase
        val _success = db.delete(TABLE_NAME, COL_ID + "=?", arrayOf(_id.toString())).toLong()
        db.close()
        return Integer.parseInt("$_success") != -1
    }

}

