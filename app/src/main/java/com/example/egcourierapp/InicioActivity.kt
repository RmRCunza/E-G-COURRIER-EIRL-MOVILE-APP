package com.example.egcourierapp

import android.os.Bundle

class InicioActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        configurarMenu()
    }
}