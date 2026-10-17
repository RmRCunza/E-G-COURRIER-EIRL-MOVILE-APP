package com.example.egcourierapp.ruta

import android.os.Bundle
import com.example.egcourierapp.BaseActivity
import com.example.egcourierapp.R

class RutaDiaActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_ruta_dia)

        configurarMenu()

        cambiarTitulo("RUTA DEL DÍA")
    }
}