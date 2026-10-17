package com.example.egcourierapp.entregar

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.egcourierapp.BaseActivity
import com.example.egcourierapp.R
import com.google.android.material.button.MaterialButton
import java.io.File
import android.content.Intent
import com.example.egcourierapp.ImagenCompletaActivity

class EntregaImgActivity : BaseActivity() {

    private lateinit var previewView: PreviewView
    private lateinit var imageCapture: ImageCapture

    private lateinit var img1: ImageView
    private lateinit var img2: ImageView
    private lateinit var img3: ImageView
    private lateinit var img4: ImageView

    private val REQUEST_CAMERA = 100

    private val fotos = mutableListOf<File>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_entrega_imagen)

        configurarMenu()

        cambiarTitulo("ENTREGA : IMAGEN")

        previewView = findViewById(R.id.previewView)

        img1 = findViewById(R.id.img1)
        img2 = findViewById(R.id.img2)
        img3 = findViewById(R.id.img3)
        img4 = findViewById(R.id.img4)

        val btnCamaraEntrega =
            findViewById<ImageButton>(R.id.btnCamaraEntrega)

        val btnGrabar =
            findViewById<MaterialButton>(R.id.btnGrabarEntrega)

        btnGrabar.visibility = View.GONE

        if (
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {

            iniciarCamara()

        } else {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_CAMERA
            )
        }

        btnCamaraEntrega.setOnClickListener {
            tomarFoto(btnGrabar)
        }

        btnGrabar.setOnClickListener {

            val vistaDialog =
                layoutInflater.inflate(
                    R.layout.dialog_confirmacion_fotos,
                    null
                )

            val dialogImg1 =
                vistaDialog.findViewById<ImageView>(
                    R.id.dialogImg1
                )

            val dialogImg2 =
                vistaDialog.findViewById<ImageView>(
                    R.id.dialogImg2
                )

            val dialogImg3 =
                vistaDialog.findViewById<ImageView>(
                    R.id.dialogImg3
                )

            val dialogImg4 =
                vistaDialog.findViewById<ImageView>(
                    R.id.dialogImg4
                )

            val vistas = listOf(
                dialogImg1,
                dialogImg2,
                dialogImg3,
                dialogImg4
            )

            fotos.forEachIndexed { index, file ->

                if (index < vistas.size) {

                    val bitmap =
                        BitmapFactory.decodeFile(
                            file.absolutePath
                        )

                    vistas[index].setImageBitmap(bitmap)
                }
            }

            AlertDialog.Builder(this)
                .setTitle("Confirmación")
                .setMessage("¿Está conforme con las imágenes?")
                .setView(vistaDialog)

                .setPositiveButton("Sí") { _, _ ->

                    Toast.makeText(
                        this,
                        "Entrega grabada correctamente",
                        Toast.LENGTH_LONG
                    ).show()

                    finish()
                }

                .setNegativeButton("No", null)
                .show()
        }
    }

    private fun iniciarCamara() {

        val cameraProviderFuture =
            ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({

            val cameraProvider =
                cameraProviderFuture.get()

            val preview =
                Preview.Builder().build()

            preview.surfaceProvider =
                previewView.surfaceProvider

            imageCapture =
                ImageCapture.Builder().build()

            val cameraSelector =
                CameraSelector.DEFAULT_BACK_CAMERA

            cameraProvider.unbindAll()

            cameraProvider.bindToLifecycle(
                this,
                cameraSelector,
                preview,
                imageCapture
            )

        }, ContextCompat.getMainExecutor(this))
    }

    private fun tomarFoto(
        btnGrabar: MaterialButton
    ) {

        if (fotos.size >= 4) {

            Toast.makeText(
                this,
                "Máximo 4 fotos permitidas",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        val fotoFile = File(
            externalMediaDirs.first(),
            "${System.currentTimeMillis()}.jpg"
        )

        val outputOptions =
            ImageCapture.OutputFileOptions.Builder(
                fotoFile
            ).build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),

            object : ImageCapture.OnImageSavedCallback {

                override fun onImageSaved(
                    outputFileResults:
                    ImageCapture.OutputFileResults
                ) {

                    fotos.add(fotoFile)

                    actualizarMiniaturas()

                    Toast.makeText(
                        this@EntregaImgActivity,
                        "Foto ${fotos.size}/4 guardada",
                        Toast.LENGTH_SHORT
                    ).show()

                    if (fotos.size == 4) {

                        btnGrabar.visibility =
                            View.VISIBLE

                        Toast.makeText(
                            this@EntregaImgActivity,
                            "Puede grabar la entrega",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onError(
                    exception: ImageCaptureException
                ) {

                    Toast.makeText(
                        this@EntregaImgActivity,
                        "Error al tomar foto",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
    }

    private fun mostrarImagenCompleta(file: File) {

        val intent = Intent(
            this,
            ImagenCompletaActivity::class.java
        )

        intent.putExtra(
            "rutaImagen",
            file.absolutePath
        )

        startActivity(intent)
    }

    private fun actualizarMiniaturas() {

        val vistas = listOf(
            img1,
            img2,
            img3,
            img4
        )

        vistas.forEach {
            it.setImageDrawable(null)
            it.setOnClickListener(null)
        }

        fotos.forEachIndexed { index, file ->

            if (index < vistas.size) {

                val bitmap =
                    BitmapFactory.decodeFile(
                        file.absolutePath
                    )

                vistas[index].setImageBitmap(bitmap)

                vistas[index].setOnClickListener {
                    mostrarImagenCompleta(file)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        super.onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults
        )

        if (
            requestCode == REQUEST_CAMERA &&
            grantResults.isNotEmpty() &&
            grantResults[0] ==
            PackageManager.PERMISSION_GRANTED
        ) {

            iniciarCamara()
        }
    }
}