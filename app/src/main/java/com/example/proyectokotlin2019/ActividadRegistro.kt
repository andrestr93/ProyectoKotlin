package com.example.proyectokotlin2019

import Adaptador
import android.app.Activity
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_actividad_registro.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.net.URL
import java.util.ArrayList

class ActividadRegistro : AppCompatActivity() {

    var coche: Coches? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_registro)


    }

    fun registro(view: View) {

        if ((editmarca.length() > 0) and (editmatricula.length() > 0) and (editmodelo.length() > 0) and (editurl.length() > 0)) {
            val url =
                "http://iesayala.ddns.net/andrestr93/php/insertcoche.php/?Marca=" + editmarca.text.toString() + "&Modelo=" + editmodelo.text.toString() + "&Matricula=" + editmatricula.text.toString() + "&Image=" + editurl.text.toString()
            leerUrl(url)
            editmarca.setText("")
            editmodelo.setText("")
            editmatricula.setText("")
            editurl.setText("")
            Snackbar.make(view, "Registro insertado correctamente", Snackbar.LENGTH_LONG).show()
        }


    }


    fun leerUrl(urlString: String): String {

        val response = try {
            URL(urlString)

                .openStream()
                .bufferedReader()
                .use { it.readText() }
        } catch (e: IOException) {
            "Error with ${e.message}."
        }

        return response
    }





    }










