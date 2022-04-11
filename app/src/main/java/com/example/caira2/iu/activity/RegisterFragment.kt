package com.example.caira2.iu.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.caira2.R
import com.example.caira2.databinding.RegisterFragmentBinding
import com.example.caira2.others.BaseFragment
import com.example.caira2.viewModel.RegisterViewModel

class RegisterFragment : BaseFragment<RegisterFragmentBinding>(R.layout.register_fragment) {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var viewModel: RegisterViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = RegisterFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.registerResponse.observe(viewLifecycleOwner, Observer { value ->
            Log.i("msg*****", "RegisterResponse: ${value} ")
            if (value) {
                Log.i("msg*****", "goto Dashboarh: ${value} ")
                val intent = Intent(activity, BodyappActivity::class.java)
                startActivity(intent)
            } else {
                //todo NO registrado, mostrar errores
                Log.i("msg*****", "goto Dashboarh: ${value} ")
    //            binding.textInputLayoutName.error="Nombre en mayusculas"
    //            binding.textInputLayoutName.error=null
                Toast.makeText(activity, "Error en registro", Toast.LENGTH_LONG).show()
            }
        })
    }
}