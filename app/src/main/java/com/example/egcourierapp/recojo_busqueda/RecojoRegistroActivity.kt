package com.example.egcourierapp.recojo_busqueda

import android.os.Bundle
import com.example.egcourierapp.BaseActivity
import com.example.egcourierapp.R

class RecojoRegistroActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_recojo_registro)

        configurarMenu()

        cambiarTitulo("RECOJO : REGISTRO")
    }
}