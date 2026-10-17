package com.example.egcourierapp.entregar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.example.egcourierapp.BaseActivity
import com.example.egcourierapp.R
import android.widget.TextView
import com.example.egcourierapp.InicioActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class EntregaDatosActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_entrega_datos)

        configurarMenu()

        cambiarTitulo("ENTREGA: DATOS")

        val etFecha = findViewById<TextView>(R.id.etFecha)
        val etHora = findViewById<TextView>(R.id.etHora)

        val fechaActual = SimpleDateFormat(
            "dd/MM/yyyy",
            Locale.getDefault()
        ).format(Date())

        val horaActual = SimpleDateFormat(
            "HH:mm",
            Locale.getDefault()
        ).format(Date())

        etFecha.setText(fechaActual)
        etHora.setText(horaActual)

        val btnGrabarEntrega = findViewById<Button>(R.id.btnGrabarEntrega)
        btnGrabarEntrega.setOnClickListener {
            val intent = Intent(this, EntregaActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}