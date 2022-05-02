package com.example.caira2.iu.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.caira2.R
import com.example.caira2.databinding.RegisterFragmentBinding
import com.example.caira2.others.BaseFragment
import com.example.caira2.viewModel.RegisterViewModel

/**
 * Vista Formulario para registro de usuario
 *
 * Observers
 * registerResponse -> Cambia de activity
 * codigoError -> Muestra mensajes de error
 */
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
        val indicator: ProgressBar = binding.circularIndicator!!
        // RESULTADO DEL  REGISTRO
        viewModel.registerResponse.observe(viewLifecycleOwner) { value ->
            Log.i("msg*****", "observer usuario registrado: ${value} ")

            if (value) {
                val intent = Intent(activity, BodyappActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(activity, "Error en registro", Toast.LENGTH_LONG).show()
            }
        }
        // MOSTRAR ERRORES
        viewModel.codigoError.observe(viewLifecycleOwner) { it ->
            Log.i("msg*****", "dentro observer ${it}")
            viewModel.mostrarErrores()
            when (it) {
                // reiniciar errores
                0 -> {
                    binding.textInputLayoutEmail.error = null
                    binding.textInputLayoutPassword.error = null
                    binding.textInputLayoutRepPassword.error =null
                    binding.textInputLayoutPrefered.error=null
                    binding.textInputLayoutName.error=null
                }
                // Errores antes de hacer la llamada
                //Email vacio
                10 -> binding.textInputLayoutEmail.error = viewModel.msgLiveData.value
                //Formato Email
                11 -> binding.textInputLayoutEmail.error = viewModel.msgLiveData.value
                //Password Vacio
                20 -> binding.textInputLayoutPassword.error = viewModel.msgLiveData.value
                //repPassword Vacio
                30 -> binding.textInputLayoutRepPassword.error = viewModel.msgLiveData.value
                //Password y repPassword distintos
                31 -> binding.textInputLayoutRepPassword.error = viewModel.msgLiveData.value
                // name Vacio
                40 -> binding.textInputLayoutName.error = viewModel.msgLiveData.value
                // preferred vacio
                50 -> binding.textInputLayoutPrefered.error = viewModel.msgLiveData.value
                // Errores devueltos por la API

                //usuario ya existe
                400 -> binding.textInputLayoutEmail.error = viewModel.msgLiveData.value
                //nombre por mayuscula
                422 -> binding.textInputLayoutName.error = viewModel.msgLiveData.value
            }
        }

        // MOSTRAR PROGRESSBAR
        viewModel.mostrarProgressBar.observe(viewLifecycleOwner) { value ->
            Log.i("msg*****", "mostrarProgressBar: ${value} ")

            if (value) {
                Log.i("msg*****", "mostrarProgressBar: ${value} ")
                binding.circularIndicator.visibility=View.VISIBLE
            } else {
                Log.i("msg*****", "mostrarProgressBar: ${value} ")
                binding.circularIndicator.visibility=View.GONE
            }
        }
    }
}
