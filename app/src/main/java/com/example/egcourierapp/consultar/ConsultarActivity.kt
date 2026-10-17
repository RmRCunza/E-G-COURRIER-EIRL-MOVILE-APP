package com.example.egcourierapp.consultar

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.example.egcourierapp.BaseActivity
import com.example.egcourierapp.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup
import android.content.Intent

class ConsultarActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_consultar)

        configurarMenu()

        cambiarTitulo("CONSULTAR SERVICIO")

        val toggle = findViewById<MaterialButtonToggleGroup>(R.id.toggleTipoBusqueda)

        val btnBuscar = findViewById<MaterialButton>(R.id.btnBuscarS)

        val layoutResultado = findViewById<LinearLayout>(R.id.layoutResultado)

        btnBuscar.setOnClickListener {

            when (toggle.checkedButtonId) {

                R.id.btnGuiaCliente -> {

                    layoutResultado.visibility = View.VISIBLE
                }

                R.id.btnGuiaEG -> {

                    layoutResultado.visibility = View.INVISIBLE
                    startActivity(
                        Intent(
                            this,
                            ConsultaEyGActivity::class.java
                        )
                    )
                }
            }
        }
        layoutResultado.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    ConsultaEyGActivity::class.java
                )
            )
        }
    }
}