package com.example.myapplication.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.FavoriAdapter
import com.example.myapplication.sql.FavoriDb


class FavoriActivity : AppCompatActivity() {
    private var rv: RecyclerView? = null
    private var adapter: FavoriAdapter? = null
    private lateinit var favoridb: FavoriDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favori)
        rv = findViewById<View>(R.id.coinListF) as RecyclerView
        rv!!.setHasFixedSize(true)
        rv!!.layoutManager = LinearLayoutManager(this)

        favoridb = FavoriDb(this)
        adapter = FavoriAdapter(favoridb.getAllFavori())
        rv!!.adapter = adapter
    }

}
