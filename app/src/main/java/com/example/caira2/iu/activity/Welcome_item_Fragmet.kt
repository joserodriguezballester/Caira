package com.example.caira2.iu.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.example.caira2.R
import com.example.caira2.model.WelcomeFrame
import kotlinx.android.synthetic.main.fragment_welcome_item.*

const val ARG_OBJECT = "object"
class Welcome_item_Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_welcome_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
       val welcomeFrame:WelcomeFrame= this.getSerializable(ARG_OBJECT) as WelcomeFrame

            imgFrame.setImageResource(welcomeFrame.imagen)
            tvTitulo.text= getString(welcomeFrame.titulo)
            tvDescripcion.text=getString(welcomeFrame.descripcion)

        }
    }
}