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
        // RESULTADO DEL  REGISTRO
        viewModel.registerResponse.observe(viewLifecycleOwner) { value ->
            Log.i("msg*****", "RegisterResponse: ${value} ")
            if (value) {
                Log.i("msg*****", "goto Dashboarh: ${value} ")
                val intent = Intent(activity, BodyappActivity::class.java)
                startActivity(intent)
            } else {
                //todo NO registrado, mostrar errores por viewBinding
                Log.i("msg*****", "goto Dashboarh: ${value} ")
                Toast.makeText(activity, "Error en registro", Toast.LENGTH_LONG).show()
            }
        }

        viewModel.codigoError.observe(viewLifecycleOwner) { it ->
            viewModel.mostrarErrores()
            Log.i("msg*****", "dentro observer ${it}")
            when (it) {
                400 -> {//usuario ya existe
                    Log.i("msg*****", "dentro del 400 ${viewModel.msgLiveData.value}")
                    binding.textInputLayoutEmail.error = viewModel.msgLiveData.value
                }
                422 -> {//nombre por mayuscula
                    binding.textInputLayoutName.error = viewModel.msgLiveData.value
                }
            }
        }
    }
}
