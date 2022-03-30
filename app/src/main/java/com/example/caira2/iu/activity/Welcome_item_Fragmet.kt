package com.example.caira2.iu.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.caira2.R
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
            tvTitulo.text= getString(ARG_OBJECT)

        }
    }
}