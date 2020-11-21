package com.example.odev4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val firstname = findViewById<EditText>(R.id.firstname).text.toString()
            val lastname = findViewById<EditText>(R.id.lastname).text.toString()
            val age = findViewById<EditText>(R.id.age).text.toString()
            val email = findViewById<EditText>(R.id.email).text.toString()
            val pass = findViewById<EditText>(R.id.pass).text.toString()
            val passAgain = findViewById<EditText>(R.id.passAgain).text.toString()


            if (!alphanumeric(firstname)) {
                message("Ad bilgisini yanlış girdiniz.")
            } else if (!alphanumeric(lastname)) {
                message("Soyad bilgisini yanlış girdiniz.")
            } else if (age.isEmpty() || !ageControl(age.toInt())) {
                message("Yaş bilgisini yanlış girdiniz.")
            } else if (!email.matches(Regex("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$"))) {
                message("hatalı e posta adresi")
            } else if (pass.length < 5) {
                message("şifre hatalı")
            } else if (pass != passAgain){
                message("şifreler aynı değil")
            }

        }

    }

    private fun alphanumeric(str: String): Boolean {
        var i = 0
        for(letter in str) {
            if (letter.isLetterOrDigit()) i++
        }
        return i >= 2
    }

    private fun message(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    private fun ageControl(age: Int): Boolean {
        return age >= 18 && age <= 100
    }



}
