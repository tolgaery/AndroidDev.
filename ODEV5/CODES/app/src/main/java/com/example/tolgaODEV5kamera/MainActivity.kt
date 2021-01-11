package com.example.tolgaODEV5kamera

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
import com.example.cameragridview.R

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        acik = findViewById(R.id.acik)
        butonn = findViewById(R.id.butonn)
        goruntu = findViewById(R.id.gridView)

        openCamera()
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {

                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    dispatchTakePictureIntent()
                } else {
                    Toast.makeText(this, "Kameraya İzin Vermelisin!", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
     private fun openCamera() {
         if (!checkCameraHardware()) {
             acik.text = "Lütfen Kameraya İzin ver!"
             acik.setTextColor(Color.RED)
         } else {
             acik.text = "İZİNLER VERİLDİ!"
             acik.setTextColor(Color.BLACK)
             butonn.setOnClickListener {
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
            goruntu.adapter = adapterr(this,images)
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
            Toast.makeText(this, "Kameraya İzin Vermeden Fotoğraf Çekemem!", Toast.LENGTH_SHORT).show()
        }
    }

    lateinit var acik: TextView
    lateinit var butonn: Button
    lateinit var goruntu: GridView
    lateinit var adapter: adapterr
    private val REQUEST_IMAGE_CAPTURE = 123
    private val CAMERA_REQUEST_CODE = 234
    private val images = arrayListOf<Bitmap>()

}