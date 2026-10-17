package com.example.egcourierapp.registrar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.example.egcourierapp.BaseActivity
import com.example.egcourierapp.R

class RegistrarDestinoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_destino)

        configurarMenu()
        cambiarTitulo("REGISTRAR SERVICIO")

        val btnAtras = findViewById<Button>(R.id.btnAtras)
        val btnSiguiente = findViewById<Button>(R.id.btnSiguiente)

        btnAtras.setOnClickListener {
            finish()
        }

        btnSiguiente.setOnClickListener {

            val intent = Intent(
                this,
                RegistrarComplementarioActivity::class.java
            )

            startActivity(intent)
        }
    }
}