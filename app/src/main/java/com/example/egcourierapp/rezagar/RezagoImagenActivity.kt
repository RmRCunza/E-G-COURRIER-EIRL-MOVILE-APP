package com.example.egcourierapp.rezagar

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
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
import com.example.egcourierapp.ImagenCompletaActivity
import com.example.egcourierapp.R
import com.google.android.material.button.MaterialButton
import java.io.File

class RezagoImagenActivity : BaseActivity() {

    private lateinit var previewView: PreviewView
    private lateinit var imageCapture: ImageCapture

    private lateinit var imgPreviewRezago: ImageView

    private var fotoFileGuardada: File? = null

    private val REQUEST_CAMERA = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_rezago_imagen)

        configurarMenu()
        cambiarTitulo("REZAGO : IMAGEN")

        previewView = findViewById(R.id.previewView)

        imgPreviewRezago =
            findViewById(R.id.imgPreviewRezago)

        val btnCamaraRezago =
            findViewById<ImageButton>(R.id.btnCamaraRezago)

        val btnGrabarRezago =
            findViewById<MaterialButton>(R.id.btnGrabarRezago)

        btnGrabarRezago.visibility = View.GONE

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

        btnCamaraRezago.setOnClickListener {
            tomarFoto(btnGrabarRezago)
        }

        btnGrabarRezago.setOnClickListener {

            val vistaDialog = layoutInflater.inflate(
                R.layout.dialog_confirmacion_rezago,
                null
            )

            val dialogImg =
                vistaDialog.findViewById<ImageView>(
                    R.id.dialogImgRezago
                )

            fotoFileGuardada?.let { file ->

                val bitmap =
                    BitmapFactory.decodeFile(
                        file.absolutePath
                    )

                dialogImg.setImageBitmap(bitmap)

                dialogImg.setOnClickListener {
                    mostrarImagenCompleta(file)
                }
            }

            val dialog = AlertDialog.Builder(this)
                .setTitle("Confirmación")
                .setMessage("¿Está conforme con la imagen?")
                .setView(vistaDialog)
                .setPositiveButton("Sí") { _, _ ->

                    Toast.makeText(
                        this,
                        "Rezago registrado correctamente",
                        Toast.LENGTH_LONG
                    ).show()

                    finish()
                }
                .setNegativeButton("No", null)
                .show()

            dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setTextColor(Color.parseColor("#097EC7"))

            dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                .setTextColor(Color.parseColor("#952f57"))
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
        btnGrabarRezago: MaterialButton
    ) {

        if (fotoFileGuardada != null) {

            Toast.makeText(
                this,
                "Solo se permite una imagen",
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

                    fotoFileGuardada = fotoFile

                    val bitmap =
                        BitmapFactory.decodeFile(
                            fotoFile.absolutePath
                        )

                    imgPreviewRezago.setImageBitmap(bitmap)

                    imgPreviewRezago.visibility =
                        View.VISIBLE

                    imgPreviewRezago.setOnClickListener {
                        mostrarImagenCompleta(fotoFile)
                    }

                    btnGrabarRezago.visibility =
                        View.VISIBLE

                    Toast.makeText(
                        this@RezagoImagenActivity,
                        "Imagen guardada",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onError(
                    exception: ImageCaptureException
                ) {

                    Toast.makeText(
                        this@RezagoImagenActivity,
                        "Error al tomar la imagen",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
    }

    private fun mostrarImagenCompleta(
        file: File
    ) {

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