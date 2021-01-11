package com.example.tolgaODEV5kamera

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.example.cameragridview.R

class adapterr(
    val context: Context,
    val photolist : ArrayList<Bitmap>
): BaseAdapter(){

    override fun getCount(): Int {
        return photolist.size
    }

    override fun getItem(position: Int): Any {
        return photolist[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = View.inflate(context, R.layout.image, null)
        view.findViewById<ImageView>(R.id.imageView).setImageBitmap(photolist[position])
        return view
    }
}

