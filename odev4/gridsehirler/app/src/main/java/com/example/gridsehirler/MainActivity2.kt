package com.example.gridsehirler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val image: Int ?= intent.getIntExtra("image", 0)
        val name=intent.getStringExtra("name")
        findViewById<TextView>(R.id.name).text=name
        findViewById<TextView>(R.id.description).text=intent.getStringExtra("description")
        findViewById<ImageView>(R.id.imageView).setImageResource(image!!)
    }
}