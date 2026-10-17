package com.example.egcourierapp.rezagar

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.egcourierapp.BaseActivity
import com.example.egcourierapp.R
import com.google.android.material.button.MaterialButton

class RezagoDatosActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_rezago_datos)

        configurarMenu()

        cambiarTitulo("REZAGO : DATOS")

        val btnGrabarRezago =
            findViewById<MaterialButton>(R.id.btnGrabarRezago)

        btnGrabarRezago.setOnClickListener {

            AlertDialog.Builder(this)
                .setTitle("Imagen de evidencia")
                .setMessage(
                    "¿Desea agregar una imagen al rezago?"
                )

                .setPositiveButton("Sí") { _, _ ->

                    startActivity(
                        Intent(
                            this,
                            RezagoImagenActivity::class.java
                        )
                    )
                }

                .setNegativeButton("No") { _, _ ->

                    Toast.makeText(
                        this,
                        "Rezago registrado correctamente",
                        Toast.LENGTH_LONG
                    ).show()

                    finish()
                }

                .show()
        }
    }
}