package com.example.caira2.iu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.caira2.R
import com.example.caira2.model.Course
import com.example.caira2.network.BASE_IMG_URL


class AdapterCourse : RecyclerView.Adapter<AdapterCourse.CourseViewHolder>() {

    var courses: MutableList<Course> = ArrayList()
    lateinit var contex: Context
    private lateinit var clickListener: ItemClickListener

    fun AdapterCourse(lista: MutableList<Course>, contexto: Context) {
        this.courses = lista
        this.contex = contexto

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_course, parent, false)

        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = courses[position]
        holder.bind(course, contex)
    }

    override fun getItemCount(): Int {
        return courses.size
    }


    fun setClickListener(itemClickListener: ItemClickListener) {
        clickListener = itemClickListener
    }

    inner class CourseViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        //Datos de la interface de cada item
        private val card = view.findViewById(R.id.card) as CardView
        private val siglas = view.findViewById(R.id.idSiglas) as TextView
        private val nombre = view.findViewById(R.id.idNombre) as TextView
        private val tipo = view.findViewById(R.id.idTipo) as TextView
        private val lugar = view.findViewById(R.id.idLocation) as TextView

        //  private val mrating = view.findViewById(R.id.ratingBar) as RatingBar
        private val precio = view.findViewById(R.id.idPrecio) as TextView
        private val imagen = view.findViewById(R.id.idImagen) as ImageView


        // Relleno de los datos de la vista con cada curso
        fun bind(course: Course, context: Context) {

            siglas.text = course.center.acronym
            nombre.text = course.name
            tipo.text = course.category
            lugar.text = course.modality
            //        mrating.rating= 4.5F
            precio.text = course.price.toString()
            val imgUrl = BASE_IMG_URL.plus(course.image.substring(1))
            Glide.with(context).load(imgUrl).into(imagen);
            itemView.setOnClickListener(View.OnClickListener {

                val position: Int = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    clickListener.onClick(it, position)
                    // el método onclick de la interfase, en todo el row (itemView)
                }
            })
        }

        override fun onClick(view: View?) {
            // call the onClick in the OnItemClickListenerTODO("Not yet implemented")
            clickListener.onClick(view, adapterPosition)
        }
    }


    interface ItemClickListener {
        fun onClick(view: View?, position: Int)
    }
}