package com.example.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.activity.FavoriActivity
import com.example.myapplication.model.CoinItem
import com.example.myapplication.model.FavoriItem
import com.example.myapplication.sql.FavoriDb
import kotlinx.android.synthetic.main.coin_card.view.*

class FavoriAdapter(private val exampleList: List<FavoriItem>) : RecyclerView.Adapter<FavoriAdapter.ExampleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.coin_card,
            parent, false
        )
        return ExampleViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]
        holder.name.text = currentItem.name
        holder.price.text = currentItem.price
        holder.rank.text = currentItem.rank


    }
    override fun getItemCount() = exampleList.size
    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.tvName
        val price : TextView = itemView.tvPrice
        val rank : TextView = itemView.tvRank
        val btnFav : ImageView = itemView.imFav

    }
}