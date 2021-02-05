package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.R
import com.example.myapplication.adapters.CoinAdapter
import com.example.myapplication.model.CoinItem
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray


class MainActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val exampleList = jsonParse()

        coinList.adapter = CoinAdapter(this,exampleList)
        coinList.layoutManager = LinearLayoutManager(this)
        coinList.setHasFixedSize(true)


        btnCamera.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }
        btnFavori.setOnClickListener {
            val intent = Intent(this, FavoriActivity::class.java)
            startActivity(intent)
        }

    }

    private fun jsonParse(): List<CoinItem> {

        val list = ArrayList<CoinItem>()
        val stringRequest = StringRequest("https://static.coinpaper.io/api/coins.json", { response ->
                    val jsonArray = JSONArray(response)
                    for (i in 0 until jsonArray.length()) {
                        val employee = jsonArray.getJSONObject(i)
                        val id = employee.getString("id")
                        val name = employee.getString("name")
                        val image = employee.getString("image")
                        val price = employee.getString("price")
                        val rank = employee.getString("rank")

                        list.add(CoinItem(id, name,image,rank,price)
                        )
                    }

            }) {
            // Anything you want
        }
        val requestQueue = Volley.newRequestQueue(applicationContext)
        requestQueue.add(stringRequest)
        progressBar.setVisibility(View.INVISIBLE)
        return list

    }


}

