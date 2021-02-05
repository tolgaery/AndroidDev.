package com.example.myapplication.sql

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.myapplication.adapters.CoinAdapter
import com.example.myapplication.model.CoinItem
import com.example.myapplication.model.FavoriItem
import com.example.myapplication.model.User
import java.util.ArrayList

class FavoriDb(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val CREATE_FAVORI_TABLE = ("CREATE TABLE " + TABLE_FAVORI + "("
            + COLUMN_COIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_COIN_NAME + " TEXT,"
            + COLUMN_COIN_PRICE + " TEXT," + COLUMN_COIN_RANK + " TEXT" + ")")


    private val DROP_TABLE_FAVORI = "DROP TABLE IF EXISTS $TABLE_FAVORI"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_FAVORI_TABLE)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        //Drop User Table if exist
        db.execSQL(DROP_TABLE_FAVORI)

        // Create tables again
        onCreate(db)

    }
    fun getAllFavori(): List<FavoriItem> {

        // array of columns to fetch
        val columns = arrayOf(
            FavoriDb.COLUMN_COIN_ID,
            FavoriDb.COLUMN_COIN_NAME,
            FavoriDb.COLUMN_COIN_RANK,
            FavoriDb.COLUMN_COIN_PRICE
        )

        // sorting orders
        val sortOrder = "${FavoriDb.COLUMN_COIN_NAME} ASC"
        val favoriList = ArrayList<FavoriItem>()

        val db = this.readableDatabase

        // query the user table
        val cursor = db.query(
            FavoriDb.TABLE_FAVORI, //Table to query
            columns,            //columns to return
            null,     //columns for the WHERE clause
            null,  //The values for the WHERE clause
            null,      //group the rows
            null,       //filter by row groups
            sortOrder)         //The sort order
        if (cursor.moveToFirst()) {
            do {
                val fav = FavoriItem(id = cursor.getString(cursor.getColumnIndex(FavoriDb.COLUMN_COIN_ID)),
                    name = cursor.getString(cursor.getColumnIndex(FavoriDb.COLUMN_COIN_NAME)),
                    rank = cursor.getString(cursor.getColumnIndex(FavoriDb.COLUMN_COIN_RANK)),
                    image = "",
                    price = cursor.getString(cursor.getColumnIndex(FavoriDb.COLUMN_COIN_PRICE)))

                favoriList.add(fav)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return favoriList
    }
    fun addFavori(coin: CoinItem) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_COIN_NAME, coin.name)
        values.put(COLUMN_COIN_PRICE, coin.price)
        values.put(COLUMN_COIN_RANK, coin.rank)

        // Inserting Row
        db.insert(TABLE_FAVORI, null, values)
        db.close()
    }
    fun checkFavori(name: String): Boolean {

        // array of columns to fetch
        val columns = arrayOf(FavoriDb.COLUMN_COIN_ID)
        val db = this.readableDatabase

        // selection criteria
        val selection = "${FavoriDb.COLUMN_COIN_NAME} = ?"

        // selection argument
        val selectionArgs = arrayOf(name)

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        val cursor = db.query(
            FavoriDb.TABLE_FAVORI, //Table to query
            columns,        //columns to return
            selection,      //columns for the WHERE clause
            selectionArgs,  //The values for the WHERE clause
            null,  //group the rows
            null,   //filter by row groups
            null)  //The sort order


        val cursorCount = cursor.count
        cursor.close()
        db.close()

        if (cursorCount > 0) {
            return true
        }

        return false
    }
    companion object {

        // Database Version
        private val DATABASE_VERSION = 1

        // Database Name
        private val DATABASE_NAME = "Favori.db"

        // User table name
        private val TABLE_FAVORI = "favori"

        //favori table columns names
        private val COLUMN_COIN_ID = "id"
        private val COLUMN_COIN_NAME = "name"
        private val COLUMN_COIN_RANK = "rank"
        private val COLUMN_COIN_PRICE = "price"

    }
}
