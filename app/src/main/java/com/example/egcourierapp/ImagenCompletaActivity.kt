package com.example.egcourierapp

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class ImagenCompletaActivity : AppCompatActivity() {

    private lateinit var imgCompleta: ImageView
    private lateinit var btnRotar: ImageButton

    private lateinit var btnSalir: ImageButton

    private var rotacionActual = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imagen_completa)

        imgCompleta = findViewById(R.id.imgCompleta)
        btnRotar = findViewById(R.id.btnRotar)

        val rutaImagen = intent.getStringExtra("rutaImagen")

        rutaImagen?.let {
            val bitmap = BitmapFactory.decodeFile(it)
            imgCompleta.setImageBitmap(bitmap)
        }

        btnRotar.setOnClickListener {
            rotacionActual += 90f

            if (rotacionActual >= 360f) {
                rotacionActual = 0f
            }

            imgCompleta.rotation = rotacionActual
        }

        btnSalir = findViewById(R.id.btnSalir)

        btnSalir.setOnClickListener {
            finish()
        }
    }
}