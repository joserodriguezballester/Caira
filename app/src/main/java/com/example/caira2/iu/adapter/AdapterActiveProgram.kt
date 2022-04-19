package com.example.caira2.iu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.caira2.R
import com.example.caira2.model.Course


class AdapterActiveProgram : RecyclerView.Adapter<AdapterActiveProgram.ActiveProgramViewHolder>() {

    var courses: MutableList<Course> = ArrayList()
    lateinit var contex: Context

    fun AdapterActiverPrograms(lista: MutableList<Course>, contexto: Context) {
        this.courses = lista
        this.contex = contexto
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActiveProgramViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_active_program_list, null, false)
        // view.setOnClickListener(this)
        return ActiveProgramViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActiveProgramViewHolder, position: Int) {
        val activeProgram = courses[position]
        holder.bind(activeProgram, contex)
    }

    override fun getItemCount(): Int {
        //TODO ver para evitar errores
        return courses.size
    }

    class ActiveProgramViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //Datos de la interface de cada item

        private val siglas = view.findViewById(R.id.idSiglas) as TextView
        private val nombre = view.findViewById(R.id.idNombre) as TextView
        private val tipo = view.findViewById(R.id.idTipo) as TextView
        private val lugar = view.findViewById(R.id.idLocation) as TextView
        private val rating = view.findViewById(R.id.ratingBar) as RatingBar
        private val precio = view.findViewById(R.id.idPrecio) as TextView
        private val imagen = view.findViewById(R.id.idImagen) as ImageView



        private lateinit var miListener: OnItemClickListener


        fun setOnItemClickListener(listener: OnItemClickListener) {
            miListener = listener
        }

        // Relleno de los datos de la interface con cada program
        fun bind(course: Course, contex: Context) {
            siglas.text=course.centerName
            nombre.text = course.name
            tipo.text = course.category
            lugar.text=course.modality
            rating.rating= 4.5F
            precio.text=course.price.toString()
   //         imagen.setImageResource(course.centerImg)

            itemView.setOnClickListener {
                //TODO SETON CLICK
                Toast.makeText(contex, course.name, Toast.LENGTH_LONG).show()
            }
            itemView.setOnClickListener(View.OnClickListener {
                if (miListener != null) {
                    val position: Int = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        miListener.onItemClick(position) // el método onclick de la interfase, en todo el row (itemView)
                    }
                }
            })
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int) // este es el método del onclick de la interfase
    }
}