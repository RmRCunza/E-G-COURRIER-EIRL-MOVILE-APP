package com.example.egcourierapp.registrar

import android.os.Bundle
import android.widget.Button
import com.example.egcourierapp.BaseActivity
import com.example.egcourierapp.R

class RegistrarComplementarioActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_complementario)

        configurarMenu()
        cambiarTitulo("REGISTRAR SERVICIO")

        val btnAtras = findViewById<Button>(R.id.btnAtras)
        val btnGrabar = findViewById<Button>(R.id.btnGrabar)

        btnAtras.setOnClickListener {
            finish()
        }

        btnGrabar.setOnClickListener {

            // Más adelante guardaremos los datos aquí supongo

        }
    }
}