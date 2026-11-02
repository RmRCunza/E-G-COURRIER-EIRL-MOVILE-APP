package com.example.egcourierapp.utils

import android.content.Context
import com.example.egcourierapp.models.UbigeoItem
import org.json.JSONArray

class UbigeoRepository(private val context: Context) {

    fun cargarUbigeos(): List<UbigeoItem> {
        val json = context.assets
            .open("ubigeos_sunat.json")
            .bufferedReader()
            .use { it.readText() }

        val jsonArray = JSONArray(json)
        val lista = mutableListOf<UbigeoItem>()

        for (i in 0 until jsonArray.length()) {
            val objeto = jsonArray.getJSONObject(i)

            val item = UbigeoItem(
                departamento = objeto.getString("departamento"),
                provincia = objeto.getString("provincia"),
                distrito = objeto.getString("distrito"),
                codigoSunat = objeto.getString("codigoSunat")
            )

            lista.add(item)
        }

        return lista
    }
}