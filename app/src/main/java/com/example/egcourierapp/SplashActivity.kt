package com.example.egcourierapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashCargaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_carga)

        Handler(Looper.getMainLooper()).postDelayed({

            startActivity(
                Intent(this, InicioActivity::class.java)
            )

            finish()

        }, 2500)

    }
}