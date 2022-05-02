package com.example.caira2.iu.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.caira2.R
import com.example.caira2.databinding.LoginFragmentBinding
import com.example.caira2.others.BaseFragment
import com.example.caira2.viewModel.LoginViewModel


/**
 * Muestra el formulario para poder hacer login
 *
 * observers: <br/>
 * gotoRegister -> Ir a formulario Register <br/>
 * loginResponse -> Ir al Dashboard <br/>
 *                   [remembertoMe] -> guardar en Share Preferents <br/>
 * codigoError -> Visibiliza errores <br/>
 *
 */
class LoginFragment : BaseFragment<LoginFragmentBinding>(R.layout.login_fragment) {


    private lateinit var viewModel: LoginViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = LoginFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.viewmodel = viewModel

        // ENLACE A REGISTRAR cambia de activity
        viewModel.gotoRegister.observe(viewLifecycleOwner) { value ->
            Log.i("msg*****", "gotoRegister: ${value} ")
            if (value) {
                Log.i("msg*****", "gotoRegister: ${value} ")
                val intent = Intent(activity, RegisterActivity::class.java)
                startActivity(intent)
            }
        }

        // RESULTADO DE LOGEARSE -> guarda en sharepreferents, cambia de activity
        viewModel.loginResponse.observe(viewLifecycleOwner) { value ->
            Log.i("msg*****", "logeado: ${value} ")
            if (value) {
                Log.i("msg*****", "gotoDashboar: ${viewModel.remembertoMe} ")
                val intent = Intent(activity, BodyappActivity::class.java)
                startActivity(intent)
            } else {
                // TODO mostrar informacion de no logueado
                Toast.makeText(context, "no logeadi", Toast.LENGTH_LONG).show()
            }
        }

        // VISIBILIZAR ERRORES
        viewModel.codigoError.observe(viewLifecycleOwner) { it ->
            Log.i("msg*****", "dentro observer ${it}")
            when (it) {
                //reinicio de la vista de errores
                null -> {
                    binding.textInputLayoutEmail.error = null
                    binding.textInputLayoutPassword.error = null
                }
                //Correo inválida
                500 -> {
                    Log.i("msg*****", "dentro del 500 ${viewModel.msgLiveData.value}")
                    binding.textInputLayoutEmail.error = viewModel.msgLiveData.value
                }
                //Contraseña inválida
                400 -> {
                    Log.i("msg*****", "dentro del 400 ${viewModel.msgLiveData.value}")
                    binding.textInputLayoutPassword.error = viewModel.msgLiveData.value
                }
                // Servidor Caido
                503 -> {
                    Log.i("msg*****", "dentro del 503 ${viewModel.msgLiveData.value}")
                    Toast.makeText(context, viewModel.msgLiveData.value, Toast.LENGTH_LONG).show()
                }
                // password vacio
                20 -> {
                    Log.i("msg*****", "dentro del 20 ${viewModel.msgLiveData.value}")
                    binding.textInputLayoutPassword.error = viewModel.msgLiveData.value
                }
                //Correo nulo
                10 -> {
                    Log.i("msg*****", "dentro del 10 ${viewModel.msgLiveData.value}")
                    binding.textInputLayoutEmail.error = viewModel.msgLiveData.value
                }
                //Correo inválida
                11 -> {
                    Log.i("msg*****", "dentro del 11 ${viewModel.msgLiveData.value}")
                    binding.textInputLayoutEmail.error = viewModel.msgLiveData.value
                }
            }
        }

        // MOSTRAR PROGRESSBAR
        viewModel.mostrarProgressBar.observe(viewLifecycleOwner) { value ->
            if (value) {
                binding.circularIndicator.visibility = View.VISIBLE
            } else {
                binding.circularIndicator.visibility = View.GONE
            }
        }

    }

}
