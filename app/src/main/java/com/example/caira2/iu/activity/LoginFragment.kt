package com.example.caira2.iu.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.caira2.R
import com.example.caira2.databinding.LoginFragmentBinding
import com.example.caira2.others.BaseFragment
import com.example.caira2.viewModel.LoginViewModel

class LoginFragment : BaseFragment<LoginFragmentBinding>(R.layout.login_fragment) {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = LoginFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.viewmodel = viewModel

        // ENLACE A REGISTRAR
        viewModel.gotoRegister.observe(viewLifecycleOwner, Observer { value ->
            Log.i("msg*****", "gotoRegister: ${value} ")
            if (value) {
                Log.i("msg*****", "gotoRegister: ${value} ")
                val intent = Intent(activity, RegisterActivity::class.java)
                startActivity(intent)
            } else {
                //todo NO registrado, cambia de actividad
            }
        })

        // RESULTADO DE LOGEARSE
        viewModel.loginResponse.observe(viewLifecycleOwner, Observer { value ->
            Log.i("msg*****", "logeado: ${value} ")
            if (value) {
                Log.i("msg*****", "gotoDashboar: ${viewModel.remembertoMe} ")
                // VALOR DEL CHECKBOX Remember Me
                if(viewModel.remembertoMe){
                    viewModel.guardarSharePreferents()
                }
                val intent = Intent(activity, BodyappActivity::class.java)
                startActivity(intent)
            }
        })

        // VISIBILIZAR ERRORES
        viewModel.codigoError.observe(viewLifecycleOwner, Observer { it ->
            Log.i("msg*****", "dentro observer ${it}")
            when (it) {
                500 -> {//Correo inválida
                    Log.i("msg*****", "dentro del 500 ${viewModel.msgLiveData.value}")
                    binding.textInputLayoutEmail.error = viewModel.msgLiveData.value
                }
                400 -> {//Contraseña inválida
                    Log.i("msg*****", "dentro del 400 ${viewModel.msgLiveData.value}")
                    binding.textInputLayoutPassword.error = viewModel.msgLiveData.value
                }
                null -> { //reinicio de la vista de errores
                    binding.textInputLayoutEmail.error =null
                    binding.textInputLayoutPassword.error =null
                }
            }
        })
    }
}