package com.example.egcourierapp.entregar

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import com.example.egcourierapp.BaseActivity
import com.example.egcourierapp.R


class EntregaTipoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_entrega_tipo)

        configurarMenu()

        cambiarTitulo("ENTREGA: TIPO")

        val btnDatosEntrega = findViewById<ImageButton>(R.id.btnDatosEntrega)
        btnDatosEntrega.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    EntregaDatosActivity::class.java
                )
            )
        }

        val btnImagenEntrega = findViewById<ImageButton>(R.id.btnImagenEntrega)
        btnImagenEntrega.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    EntregaImgActivity::class.java
                )
            )
        }
    }
}