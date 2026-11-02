
package com.example.egcourierapp.registrar

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.egcourierapp.BaseActivity
import com.example.egcourierapp.R
import com.example.egcourierapp.utils.UbigeoSpinnerController
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class RegistrarOrigenActivity : BaseActivity() {

    private var codigoUbigeoOrigen: String? = null
    private lateinit var ubigeoController: UbigeoSpinnerController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_registrar_origen)

        configurarMenu()
        cambiarTitulo("REGISTRAR SERVICIO")

        // Fecha automática
        val tvFecha = findViewById<TextView>(R.id.tvFecha)

        val fechaActual = SimpleDateFormat(
            "dd/MM/yyyy",
            Locale.getDefault()
        ).format(Date())

        tvFecha.text = fechaActual

        // Configuración de los selectores de ubicación
        ubigeoController = UbigeoSpinnerController(
            context = this,
            spDepartamento = findViewById(R.id.spDepartamento),
            spProvincia = findViewById(R.id.spProvincia),
            spDistrito = findViewById(R.id.spDistrito)
        ) { ubigeo ->

            codigoUbigeoOrigen = ubigeo?.codigoSunat

            if (ubigeo != null) {
                println("Ubigeo origen: ${ubigeo.codigoSunat}")
            }
        }

        // Botón siguiente
        findViewById<android.widget.Button>(R.id.btnSiguiente)
            .setOnClickListener {

                val ubigeo =
                    ubigeoController.obtenerUbigeoSeleccionado()

                if (ubigeo == null) {
                    Toast.makeText(
                        this,
                        "Selecciona el departamento, provincia y distrito",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@setOnClickListener
                }

                codigoUbigeoOrigen = ubigeo.codigoSunat

                val intent = Intent(
                    this,
                    RegistrarDestinoActivity::class.java
                )

                intent.putExtra(
                    "ubigeo_origen",
                    codigoUbigeoOrigen
                )

                startActivity(intent)
            }
    }
}
