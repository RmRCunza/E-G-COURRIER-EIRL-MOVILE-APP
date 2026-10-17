package com.example.egcourierapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {

            val usuario = etUsuario.text.toString().trim()
            val password = etPassword.text.toString().trim()

            val usuarioCorrecto = "admin"
            val passwordCorrecta = "1234"

            when {
                usuario.isEmpty() -> {
                    etUsuario.error = "Ingrese su usuario"
                    etUsuario.requestFocus()
                }

                password.isEmpty() -> {
                    etPassword.error = "Ingrese su contraseña"
                    etPassword.requestFocus()
                }

                usuario != usuarioCorrecto -> {
                    etUsuario.error = "Usuario incorrecto"
                    etUsuario.requestFocus()
                }

                password != passwordCorrecta -> {
                    etPassword.error = "Contraseña incorrecta"
                    etPassword.requestFocus()
                }

                else -> {
                    val intent = Intent(
                        this,
                        SplashCargaActivity::class.java
                    )
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}