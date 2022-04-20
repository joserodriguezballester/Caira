package com.example.caira2.iu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.caira2.R
import com.example.caira2.model.Course


class AdapterActiveProgram : RecyclerView.Adapter<AdapterActiveProgram.ActiveProgramViewHolder>() {

    var courses: MutableList<Course> = ArrayList()
    lateinit var contex: Context
    private lateinit var clickListener: ItemClickListener

    fun AdapterActiverPrograms(lista: MutableList<Course>, contexto: Context) {
        this.courses = lista
        this.contex = contexto
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActiveProgramViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_active_program_list, null, false)

        return ActiveProgramViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActiveProgramViewHolder, position: Int) {
        val activeProgram = courses[position]

        holder.bind(activeProgram, contex)
    }

    override fun getItemCount(): Int {
        return courses.size
    }


    fun setClickListener(itemClickListener: ItemClickListener) {
       clickListener = itemClickListener
    }


    inner class ActiveProgramViewHolder(view: View) : RecyclerView.ViewHolder(view),View.OnClickListener {
        //Datos de la interface de cada item

        private val siglas = view.findViewById(R.id.idSiglas) as TextView
        private val nombre = view.findViewById(R.id.idNombre) as TextView
        private val tipo = view.findViewById(R.id.idTipo) as TextView
        private val lugar = view.findViewById(R.id.idLocation) as TextView
        //  private val mrating = view.findViewById(R.id.ratingBar) as RatingBar
        private val precio = view.findViewById(R.id.idPrecio) as TextView
        private val imagen = view.findViewById(R.id.idImagen) as ImageView

        // Relleno de los datos de la interface con cada curso
        fun bind(course: Course, contex: Context) {

            siglas.text = course.centerName
            nombre.text = course.name
            tipo.text = course.category
            lugar.text = course.modality
            //        mrating.rating= 4.5F
            precio.text = course.price.toString()
            //         imagen.setImageResource(course.centerImg)

            itemView.setOnClickListener(View.OnClickListener {

                    val position: Int = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        clickListener.onClick(it,position) // el método onclick de la interfase, en todo el row (itemView)
                    }

            })
        }

        override fun onClick(view: View?) {
            clickListener.onClick(view, adapterPosition); // call the onClick in the OnItemClickListenerTODO("Not yet implemented")
        }
    }

 interface ItemClickListener {
    fun onClick(view: View?, position: Int)
}
}