package com.example.egcourierapp.utils

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.egcourierapp.models.UbigeoItem

class UbigeoSpinnerController(
    private val context: Context,
    private val spDepartamento: Spinner,
    private val spProvincia: Spinner,
    private val spDistrito: Spinner,
    private val onUbigeoSeleccionado: (UbigeoItem?) -> Unit
) {

    private val listaUbigeos: List<UbigeoItem> =
        UbigeoRepository(context).cargarUbigeos()

    private var ubigeoSeleccionado: UbigeoItem? = null

    private val placeholderDepartamento = "Seleccionar departamento"
    private val placeholderProvincia = "Seleccionar provincia"
    private val placeholderDistrito = "Seleccionar distrito"

    init {
        configurarSpinnerInicial()
        configurarEventos()
    }

    private fun configurarSpinnerInicial() {
        cargarOpciones(
            spDepartamento,
            listOf(placeholderDepartamento) +
                    listaUbigeos.map { it.departamento }.distinct().sorted()
        )

        cargarOpciones(spProvincia, listOf(placeholderProvincia))
        cargarOpciones(spDistrito, listOf(placeholderDistrito))
    }

    private fun configurarEventos() {

        spDepartamento.onItemSelectedListener =
            listener { posicion ->

                val departamento = spDepartamento.selectedItem.toString()

                ubigeoSeleccionado = null
                onUbigeoSeleccionado(null)

                cargarOpciones(
                    spProvincia,
                    listOf(placeholderProvincia) +
                            listaUbigeos
                                .filter { it.departamento == departamento }
                                .map { it.provincia }
                                .distinct()
                                .sorted()
                )

                cargarOpciones(
                    spDistrito,
                    listOf(placeholderDistrito)
                )
            }

        spProvincia.onItemSelectedListener =
            listener { posicion ->

                val departamento = spDepartamento.selectedItem.toString()
                val provincia = spProvincia.selectedItem.toString()

                ubigeoSeleccionado = null
                onUbigeoSeleccionado(null)

                cargarOpciones(
                    spDistrito,
                    listOf(placeholderDistrito) +
                            listaUbigeos
                                .filter {
                                    it.departamento == departamento &&
                                            it.provincia == provincia
                                }
                                .map { it.distrito }
                                .distinct()
                                .sorted()
                )
            }

        spDistrito.onItemSelectedListener =
            listener { posicion ->

                val departamento = spDepartamento.selectedItem.toString()
                val provincia = spProvincia.selectedItem.toString()
                val distrito = spDistrito.selectedItem.toString()

                ubigeoSeleccionado = listaUbigeos.firstOrNull {
                    it.departamento == departamento &&
                            it.provincia == provincia &&
                            it.distrito == distrito
                }

                onUbigeoSeleccionado(ubigeoSeleccionado)
            }
    }

    private fun cargarOpciones(
        spinner: Spinner,
        opciones: List<String>
    ) {
        val adapter = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_item,
            opciones
        )

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        spinner.adapter = adapter
        spinner.setSelection(0)
    }

    private fun listener(
        accion: (Int) -> Unit
    ): android.widget.AdapterView.OnItemSelectedListener {

        return object : android.widget.AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: android.widget.AdapterView<*>?,
                view: android.view.View?,
                position: Int,
                id: Long
            ) {
                if (position > 0) {
                    accion(position)
                }
            }

            override fun onNothingSelected(
                parent: android.widget.AdapterView<*>?
            ) {}
        }
    }

    fun obtenerUbigeoSeleccionado(): UbigeoItem? {
        return ubigeoSeleccionado
    }
}