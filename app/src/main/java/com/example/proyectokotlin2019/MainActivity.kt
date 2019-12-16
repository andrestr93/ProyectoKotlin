package com.example.proyectokotlin2019

import android.app.Activity
import Adaptador
import android.content.Intent
import android.graphics.*
import android.os.AsyncTask
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_actividad_registro.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.net.URL
import java.text.FieldPosition
import java.util.ArrayList
import kotlin.math.log

class MainActivity : AppCompatActivity() {



    private val secondactivity = 1
    val coches = ArrayList<Coches>()
    private var adaptador : Adaptador?=null
    private var view: View? = null
    private val p = Paint()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val recyclerView: RecyclerView = findViewById(R.id.reciclercoches)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)



    }



    fun agrega(view: View) {

        butagregar.animate()
            .rotation(180f)

        val intent = Intent(this, ActividadRegistro::class.java)

        startActivity(intent)

    }



    fun consulta(view: View) {


        try {
            val gson = Gson()

            val json = leerUrl("http://iesayala.ddns.net/andrestr93/php/json.php")

            val coche = gson.fromJson(json, ArrayCoches::class.java)
            for (item in coche.coches!!.iterator()) {

                coches.add(Coches(item.Marca, item.Modelo, item.Matricula, item.Image))


                adaptador = Adaptador(coches)


                reciclercoches.adapter = adaptador
                initSwipe()
            }
        } catch (e: Exception) {
            Log.d("RESULTADO", "error")
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
        }
    }


       fun  leerUrl(urlString: String): String {

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




    private fun initSwipe() {
        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int ) {
                val position = viewHolder.adapterPosition

                if (direction == ItemTouchHelper.LEFT) {
                    adaptador!!.removeItem(position)




                } else {
                    adaptador!!.removeItem(position)
                }
            }

            private fun removeView() {
                if (view!!.parent != null) {
                    (view!!.parent as ViewGroup).removeView(view)
                }
            }





            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {


                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                    val itemView = viewHolder.itemView
                    val height = itemView.bottom.toFloat() - itemView.top.toFloat()
                    val width = height / 3

                    if (dX > 0) {
                        val icon: Bitmap
                        p.color = Color.parseColor("#388EFF")
                        val background = RectF(itemView.left.toFloat(), itemView.top.toFloat(), dX, itemView.bottom.toFloat())
                        c.drawRect(background, p)
                        icon = BitmapFactory.decodeResource(resources, R.drawable.borrar2)
                        val icon_dest = RectF(itemView.left.toFloat() + width, itemView.top.toFloat() + width, itemView.left.toFloat() + 2 * width, itemView.bottom.toFloat() - width)
                        c.drawBitmap(icon, null, icon_dest, p)

                    } else {
                        val icon: Bitmap
                        p.color = Color.parseColor("#D32F2F")
                        val background = RectF(itemView.right.toFloat() + dX, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
                        c.drawRect(background, p)
                       // icon = BitmapFactory.decodeResource(resources, R.drawable.chico)
                        val icon_dest = RectF(itemView.right.toFloat() - 2 * width, itemView.top.toFloat() + width, itemView.right.toFloat() - width, itemView.bottom.toFloat() - width)
                        //   c.drawBitmap(icon, null, icon_dest, p)
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(reciclercoches)
    }










}



