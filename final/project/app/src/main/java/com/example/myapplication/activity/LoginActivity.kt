package com.example.myapplication.activity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.myapplication.R
import com.example.myapplication.sql.DatabaseHelper

class LoginActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private val CHANNEL_ID = "channel_id_coin";
    private val notificationId = 101

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        databaseHelper = DatabaseHelper(this)
        val registerText = findViewById<TextView>(R.id.tvRegister)
        val loginBtn = findViewById<TextView>(R.id.btnLogin)

        val textEmail = findViewById<EditText>(R.id.etEmail)
        val textSifre = findViewById<EditText>(R.id.etPassword)

        registerText.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        createNotification()
        loginBtn.setOnClickListener {
            if (databaseHelper!!.checkUser(textEmail!!.text.toString().trim { it <= ' ' }, textSifre!!.text.toString().trim { it <= ' ' })) {
                val accountsIntent = Intent(this, MainActivity::class.java)
                sendNotification()
                accountsIntent.putExtra("EMAIL", textEmail!!.text.toString().trim { it <= ' ' })
                startActivity(accountsIntent)
            } else {
                val toast = Toast.makeText(applicationContext, "Bilgileri Tekrar Kontrol Ediniz", Toast.LENGTH_SHORT)
                toast.show()
            }
        }

    }

    private fun createNotification()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            val name = "Hoş Geldiniz"
            val descriptiontext = "Coin List Uygulamasına hoş geldiniz."
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID,name,importance).apply {
                description=descriptiontext
            }
            val notificationManager : NotificationManager =  getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    private fun sendNotification()
    {
        val builder = NotificationCompat.Builder(this,CHANNEL_ID)
            .setSmallIcon(R.drawable.coin)
            .setContentTitle("Hoş Geldiniz")
            .setContentText("Coin List Uygulamasına hoş geldiniz.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(this))
        {
            notify(notificationId,builder.build())
        }
    }
}