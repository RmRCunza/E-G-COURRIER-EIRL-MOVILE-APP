package com.example.egcourierapp.rezagar

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.example.egcourierapp.BaseActivity
import com.example.egcourierapp.R
import com.example.egcourierapp.consultar.ConsultaEyGActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup

class RezagoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_rezago)

        configurarMenu()

        cambiarTitulo("REZAGO: VALIDACION")

        val toggle = findViewById<MaterialButtonToggleGroup>(R.id.toggleTipoBusqueda)

        val btnBuscar = findViewById<MaterialButton>(R.id.btnBuscarS)

        val layoutResultado = findViewById<LinearLayout>(R.id.layoutResultado)

        btnBuscar.setOnClickListener {

            when (toggle.checkedButtonId) {

                R.id.btnGuiaCliente -> {

                    layoutResultado.visibility = View.VISIBLE
                }

                R.id.btnGuiaEG -> {

                    startActivity(
                        Intent(
                            this,
                            RezagoDatosActivity::class.java
                        )
                    )
                }
            }
        }
        layoutResultado.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    RezagoDatosActivity::class.java
                )
            )
        }
    }
}