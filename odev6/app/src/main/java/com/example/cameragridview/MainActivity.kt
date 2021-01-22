package com.example.cameragridview

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

     lateinit var aciklama: TextView
     lateinit var cameraButton: Button
     lateinit var gridView: GridView
     lateinit var adapter: photoadapter
     private val REQUEST_IMAGE_CAPTURE = 123
     private val CAMERA_REQUEST_CODE = 234
     private val images = arrayListOf<Bitmap>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        aciklama = findViewById(R.id.aciklama)
        cameraButton = findViewById(R.id.cameraButton)
        gridView = findViewById(R.id.gridView)

        openCamera()
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {

                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    dispatchTakePictureIntent()
                } else {
                    Toast.makeText(this, "Kullanıcı yetkisi  yok!", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
     private fun openCamera() {
         if (!checkCameraHardware()) {
             aciklama.text = "Cihazda kamera algılanmadı!"
             aciklama.setTextColor(Color.RED)
         } else {
             aciklama.text = "Kullanılabilir!"
             aciklama.setTextColor(Color.BLACK)
             cameraButton.setOnClickListener {
                 if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                     ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
                 } else {
                     dispatchTakePictureIntent()
                 }
             }
         }
     }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            images.add(imageBitmap)
            gridView.adapter = photoadapter(this,images)
        }
    }
    private fun checkCameraHardware(): Boolean {
        if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA))
            return true
        return false
    }
    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Bir sorun oluştu!", Toast.LENGTH_SHORT).show()
        }
    }
}