package com.rgraylemon.example.camera_test

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    lateinit var ocrUtil: OCRUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ocrUtil = OCRUtil(applicationContext)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val imageView = findViewById<ImageView>(R.id.ivCamera)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data!!.extras!!.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)

            //imageBitmapをOCRする処理が必要？
            imageBitmap?.let{
                println(ocrUtil.getString(applicationContext, it, OCRUtil.Companion.LangType.getLangType("jpn").str))
/*                    ocr.text = ocrUtil.getString(applicationContext, it, OCRUtil.Companion.LangType.getLangType("jap").str)
                } ?: run {
                    ocr.text = "bitmap is null"*/
            }
        }
    }
    val REQUEST_IMAGE_CAPTURE = 1

    fun onCameraImageClick(view: View) {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }
}