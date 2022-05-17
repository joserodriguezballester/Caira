package com.example.caira2.iu.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.caira2.databinding.FragmentWelcomeItemBinding
import com.example.caira2.model.WelcomeFrame


const val ARG_OBJECT = "object"

class Welcome_item_Fragment : Fragment() {
    private var _binding: FragmentWelcomeItemBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeItemBinding.inflate(layoutInflater, container, false)
        return binding.root
        //   return inflater.inflate(R.layout.fragment_welcome_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            val welcomeFrame: WelcomeFrame = this.getSerializable(ARG_OBJECT) as WelcomeFrame

            binding.imgFrame.setImageResource(welcomeFrame.imagen)
            binding.tvTitulo.text = getString(welcomeFrame.titulo)
            binding.tvDescripcion.text = getString(welcomeFrame.descripcion)

        }
    }
}