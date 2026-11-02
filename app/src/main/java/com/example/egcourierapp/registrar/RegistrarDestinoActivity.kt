
package com.example.egcourierapp.registrar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.egcourierapp.BaseActivity
import com.example.egcourierapp.R
import com.example.egcourierapp.utils.UbigeoSpinnerController

class RegistrarDestinoActivity : BaseActivity() {

    private var codigoUbigeoOrigen: String? = null
    private var codigoUbigeoDestino: String? = null

    private lateinit var ubigeoController: UbigeoSpinnerController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_registrar_destino)

        configurarMenu()
        cambiarTitulo("REGISTRAR SERVICIO")

        // Recuperar el código SUNAT del origen
        codigoUbigeoOrigen =
            intent.getStringExtra("ubigeo_origen")

        // Configurar selectores de ubicación del destino
        ubigeoController = UbigeoSpinnerController(
            context = this,
            spDepartamento = findViewById(R.id.spDepartamentoDestino),
            spProvincia = findViewById(R.id.spProvinciaDestino),
            spDistrito = findViewById(R.id.spDistritoDestino)
        ) { ubigeo ->

            codigoUbigeoDestino = ubigeo?.codigoSunat

            if (ubigeo != null) {
                println("Ubigeo destino: ${ubigeo.codigoSunat}")
            }
        }

        // Botón atrás
        val btnAtras = findViewById<Button>(R.id.btnAtras)

        btnAtras.setOnClickListener {
            finish()
        }

        // Botón siguiente
        val btnSiguiente = findViewById<Button>(R.id.btnSiguiente)

        btnSiguiente.setOnClickListener {

            val ubigeoDestino =
                ubigeoController.obtenerUbigeoSeleccionado()

            if (codigoUbigeoOrigen.isNullOrBlank()) {
                Toast.makeText(
                    this,
                    "No se recibió el código de ubicación de origen",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (ubigeoDestino == null) {
                Toast.makeText(
                    this,
                    "Selecciona el departamento, provincia y distrito de destino",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            codigoUbigeoDestino = ubigeoDestino.codigoSunat

            // Abrir pantalla complementaria
            val intentComplementario = Intent(
                this,
                RegistrarComplementarioActivity::class.java
            )

            intentComplementario.putExtra(
                "ubigeo_origen",
                codigoUbigeoOrigen
            )

            intentComplementario.putExtra(
                "ubigeo_destino",
                codigoUbigeoDestino
            )

            startActivity(intentComplementario)
        }
    }
}
