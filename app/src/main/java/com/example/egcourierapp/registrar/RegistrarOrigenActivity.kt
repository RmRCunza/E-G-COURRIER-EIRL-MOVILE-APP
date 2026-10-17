package com.example.egcourierapp.registrar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.egcourierapp.BaseActivity
import com.example.egcourierapp.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class RegistrarOrigenActivity : BaseActivity() {

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

        // Botón siguiente
        val btnSiguiente = findViewById<Button>(R.id.btnSiguiente)

        btnSiguiente.setOnClickListener {

            val intent = Intent(
                this,
                RegistrarDestinoActivity::class.java
            )

            startActivity(intent)
        }
    }
}