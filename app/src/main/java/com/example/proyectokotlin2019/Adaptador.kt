import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectokotlin2019.Coches
import com.example.proyectokotlin2019.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_main.view.*


class Adaptador(var lista: ArrayList<Coches>): RecyclerView.Adapter<Adaptador.MiViewHolder>() {
    class MiViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        fun enlazaItems(datos: Coches) {
            val titulo: TextView = itemView.findViewById(R.id.titulo)
            val imagen: ImageView = itemView.findViewById(R.id.img)

            titulo.text = datos.Modelo


            Picasso.get().load(datos.Image).into(imagen)
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "Marca ${datos.Marca} , Modelo ${datos.Modelo} , Matricula ${datos.Matricula}", Toast.LENGTH_LONG)
                    .show()
            }

        }






    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.content_main,parent,false)

        return MiViewHolder(v)
    }

    override fun getItemCount(): Int {
        return lista.size
    }


    ////////////MiViewHolder.ViewHolder
    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {

        holder.enlazaItems(lista.get(position))



    }


    fun removeItem(position: Int) {
        lista.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, lista.size)
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Mantiene el TextView
        val imagen= view.img




    }
}




