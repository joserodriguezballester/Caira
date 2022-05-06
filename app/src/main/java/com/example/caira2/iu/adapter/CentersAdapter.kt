package com.example.caira2.iu.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.caira2.R
import com.example.caira2.databinding.ItemCenterBinding
import com.example.caira2.model.Center
import com.example.caira2.model.Course
import kotlinx.android.synthetic.main.item_center.view.*

/**
 * Adaptador
 */
class CentersAdapter(
    private val centers: List<Center>,
    private val listener: RecyclerViewCentersClickListener
) : RecyclerView.Adapter<CentersAdapter.CentersViewHolder>() {

    override fun getItemCount() = centers.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CentersViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_center, parent, false
            )
        )

    override fun onBindViewHolder(holder: CentersViewHolder, position: Int) {
        //relacionar vista del item con los datos
        holder.itemCenterBinding.centre = centers[position] //centre es la variable del XML
        Glide.with(holder.itemView).load( centers[position].imgUrl).into(holder.itemView.imageView);
        holder.itemCenterBinding.root.setOnClickListener {
            Log.i("msg*****", "click item")
            listener.onRecyclerViewItemClick(holder.itemView,centers[position])
        }
        holder.itemCenterBinding.buttonCourses.setOnClickListener{
            Log.i("msg*****", "click courses")
            listener.onRecyclerViewItemClick(holder.itemView,centers[position])
        }
        holder.itemCenterBinding.buttonInfo.setOnClickListener{
            Log.i("msg*****", "click buttonInfo")
            listener.onRecyclerViewItemClick(holder.itemView,centers[position])
        }
        holder.itemCenterBinding.buttonFeed.setOnClickListener{
            Log.i("msg*****", "click buttonFeed")
            listener.onRecyclerViewItemClick(holder.itemView,centers[position])
        }

    }

    /**
     * ViewHolder de los Centros Educativos
     * bindeamos xml de cada item
     */
    inner class CentersViewHolder(val itemCenterBinding: ItemCenterBinding) :
        RecyclerView.ViewHolder(itemCenterBinding.root)
}

interface RecyclerViewCentersClickListener {
    fun onRecyclerViewItemClick(view: View, center:Center)
}