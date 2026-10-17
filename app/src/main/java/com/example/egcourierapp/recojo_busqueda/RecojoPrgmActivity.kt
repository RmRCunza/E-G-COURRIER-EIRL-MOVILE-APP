package com.example.egcourierapp.recojo_busqueda

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.egcourierapp.BaseActivity
import com.example.egcourierapp.R
import com.google.android.material.button.MaterialButton

class RecojoPrgmActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_recojo_programado)

        configurarMenu()

        cambiarTitulo("RECOJO PROGRAMADO")

        val btnBuscarRecojo =
            findViewById<MaterialButton>(R.id.btnBuscarRecojo)

        val layoutResultado1 =
            findViewById<LinearLayout>(R.id.layoutResultado1)

        val layoutResultado2 =
            findViewById<LinearLayout>(R.id.layoutResultado2)

        val tvRegistrarRecojo =
            findViewById<TextView>(R.id.tvRegistrarRecojo)

        btnBuscarRecojo.setOnClickListener {

            layoutResultado1.visibility = View.VISIBLE
            layoutResultado2.visibility = View.VISIBLE
            tvRegistrarRecojo.visibility = View.VISIBLE
        }

        tvRegistrarRecojo.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    RecojoRegistroActivity::class.java
                )
            )
        }
    }
}