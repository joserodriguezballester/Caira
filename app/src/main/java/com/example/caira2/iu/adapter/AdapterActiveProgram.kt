package com.example.caira2.iu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.caira2.R
import com.example.caira2.model.ActiveProgram


class AdapterActiveProgram : RecyclerView.Adapter<AdapterActiveProgram.ActiveProgramViewHolder>() {

    var activePrograms: MutableList<ActiveProgram> = ArrayList()
    lateinit var contex: Context

    fun AdapterActiverPrograms(lista: MutableList<ActiveProgram>, contexto: Context) {
        this.activePrograms = lista
        this.contex = contexto
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActiveProgramViewHolder {
        var view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_active_program_list, null, false)
        // view.setOnClickListener(this)
        return ActiveProgramViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ActiveProgramViewHolder,
        position: Int
    ) {
        val activeProgram = activePrograms[position]
        holder.bind(activeProgram, contex)
    }

    override fun getItemCount(): Int {
        //TODO ver para evitar errores
        return activePrograms.size
    }

    class ActiveProgramViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //Datos de la interface de cada item
        private val nombreActiveProgram = view.findViewById(R.id.idNombre) as TextView
        private val info = view.findViewById(R.id.idInfo) as TextView
        private val imagen = view.findViewById(R.id.idImagen) as ImageView

        // Relleno de los datos de la interface con cada program
        fun bind(activeProgram: ActiveProgram, contex: Context) {
            nombreActiveProgram.text = activeProgram.nombreActiveProgram
            info.text = activeProgram.university
            imagen.setImageResource(activeProgram.imagen)
            itemView.setOnClickListener {
                //TODO SETON CLICK
                Toast.makeText(contex, activeProgram.nombreActiveProgram, Toast.LENGTH_LONG).show()
            }
        }
    }
}