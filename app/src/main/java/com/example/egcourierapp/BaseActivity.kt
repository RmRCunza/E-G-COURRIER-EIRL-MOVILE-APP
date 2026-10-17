package com.example.egcourierapp

import android.content.Intent
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import android.widget.TextView
import com.example.egcourierapp.consultar.ConsultarActivity
import com.example.egcourierapp.entregar.EntregaActivity
import com.example.egcourierapp.rezagar.RezagoActivity
import com.example.egcourierapp.registrar.RegistrarOrigenActivity
import com.example.egcourierapp.ruta.RutaDiaActivity
import com.example.egcourierapp.recojo_busqueda.RecojoPrgmActivity

open class BaseActivity : AppCompatActivity() {

    protected fun configurarMenu() {

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val btnMenu = findViewById<ImageButton>(R.id.btnMenu)

        btnMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        findViewById<LinearLayout>(R.id.opConsultar)?.setOnClickListener {
            startActivity(Intent(this, ConsultarActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.opRegistrar)?.setOnClickListener {
            startActivity(Intent(this, RegistrarOrigenActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.opRecojo)?.setOnClickListener {
            startActivity(Intent(this, RecojoPrgmActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.opEntrega)?.setOnClickListener {
            startActivity(Intent(this, EntregaActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.opRezago)?.setOnClickListener {
            startActivity(Intent(this, RezagoActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.opRutaDia)?.setOnClickListener {
            startActivity(Intent(this, RutaDiaActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.opSalir)?.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)

            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
        }
    }
    protected fun cambiarTitulo(titulo: String) {

        val tvTitulo = findViewById<TextView>(R.id.tvTitulo)

        tvTitulo?.text = titulo
    }
}
