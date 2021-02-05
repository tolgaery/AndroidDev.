package com.example.myapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.model.User
import com.example.myapplication.sql.DatabaseHelper
import com.google.android.material.snackbar.Snackbar

class RegisterActivity : AppCompatActivity() {
    private lateinit var databaseHelper: DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        databaseHelper = DatabaseHelper(this)
        val loginText = findViewById<TextView>(R.id.tvLogin)
        val registerBtn = findViewById<TextView>(R.id.btnRegister)

        val textAdSoyad = findViewById<EditText>(R.id.etAdSoyad)
        val textEmail = findViewById<EditText>(R.id.etEmail)
        val textSifre = findViewById<EditText>(R.id.etPassword)

        loginText.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        registerBtn.setOnClickListener {
            if (!databaseHelper!!.checkUser(textEmail!!.text.toString().trim())) {

                var user = User(name = textAdSoyad!!.text.toString().trim(),
                    email = textEmail!!.text.toString().trim(),
                    password = textSifre!!.text.toString().trim())

                databaseHelper!!.addUser(user)

                val toast = Toast.makeText(applicationContext, "Kayıt İşlemi Başarılı", Toast.LENGTH_SHORT)
                toast.show()


            } else {
                val toast = Toast.makeText(applicationContext, "E-mail zaten var", Toast.LENGTH_SHORT)
                toast.show()
            }
        }


    }
}