package com.example.egcourierapp.consultar

import android.os.Bundle
import com.example.egcourierapp.BaseActivity
import com.example.egcourierapp.R
import com.google.android.material.button.MaterialButton

class ConsultaEyGActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_consultar_eyg)

        configurarMenu()

        cambiarTitulo("SERVICIO: XXX-XXXX")

        val btnNuevaBusqueda =
            findViewById<MaterialButton>(R.id.btnNuevaBusqueda)

        btnNuevaBusqueda.setOnClickListener {

            finish()
        }
    }
}