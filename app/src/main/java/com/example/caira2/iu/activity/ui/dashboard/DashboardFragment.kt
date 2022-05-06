package com.example.caira2.iu.activity.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.caira2.R
import com.example.caira2.databinding.FragmentDashboardBinding
import com.example.caira2.iu.adapter.AdapterActiveProgram


class DashboardFragment : Fragment(), AdapterActiveProgram.ItemClickListener  {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel:DashboardViewModel
    private val adapter = AdapterActiveProgram()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i("msg*****", "creando dashboard ")
        viewModel = ViewModelProvider(this)[DashboardViewModel::class.java]
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.llenarDatos()
        viewModel.courses.observe(viewLifecycleOwner) { datos ->
            adapter.AdapterActiveProgram(datos, requireContext())
            binding.reciclerIdPrograms.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
               // adapter.AdapterActiveProgram(datos, requireContext())
                it.adapter = adapter
            }
            adapter.setClickListener(this)
        }
    }

    override fun onClick(view: View?, position: Int) {
        Log.i(
            "msg*****",
            " onClick(view: View?, position: Int) ${position}:::${adapter.courses[position]}"
        )
        Toast.makeText(context, "tarjeta Clickeada $position", Toast.LENGTH_LONG).show()

//        // Instancias un fragment, aquí se envían los datos, suponiendo que se llame FragmentDetalle:

        val detailCourseFragment: DetailCourseFragment =
            DetailCourseFragment.newInstance(position, adapter.courses[position])


        val fragmentManager: FragmentManager? = fragmentManager
        fragmentManager?.beginTransaction()?.replace(R.id.fragmentDashboard, detailCourseFragment)
            ?.addToBackStack("atras")?.commit()

    }


}