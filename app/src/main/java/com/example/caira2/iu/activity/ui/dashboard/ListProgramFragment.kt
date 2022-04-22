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
import androidx.recyclerview.widget.RecyclerView
import com.example.caira2.R
import com.example.caira2.databinding.FragmentListProgramBinding
import com.example.caira2.iu.adapter.AdapterActiveProgram


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListProgramFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class ListProgramFragment : Fragment(), AdapterActiveProgram.ItemClickListener {
    private lateinit var viewModel: DashboardViewModel

    //  private var _binding: FragmentDashboardBinding? = null
    private var _binding: FragmentListProgramBinding? = null
    private val binding get() = _binding!!

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var recylerActivePrograms: RecyclerView

    // var activeProgramsList= mutableListOf<Course>()
    private val adapter = AdapterActiveProgram()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //   _binding = FragmentDashboardBinding.inflate(inflater)
        _binding = FragmentListProgramBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        recylerActivePrograms = binding.reciclerIdPrograms
//        recylerActivePrograms.apply {
//            layoutManager = LinearLayoutManager(context)
//            this.setHasFixedSize(true)
//        }
        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        viewModel.llenarDatos()
        //    llenarDatos()
        viewModel.text.observe(viewLifecycleOwner) {datos ->
            adapter.AdapterActiveProgram(datos, requireContext())
            binding.reciclerIdPrograms.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                adapter.AdapterActiveProgram(datos, requireContext())
               it.adapter=adapter
            }
         //   adapter.AdapterActiverPrograms(datos, requireContext())
        //    recylerActivePrograms.adapter = adapter
            adapter.setClickListener(this)
        }
    }


    companion object {

        fun newInstance() =
            ListProgramFragment()
    }

    override fun onClick(view: View?, position: Int) {
        Log.i("msg*****", " onClick(view: View?, position: Int) ${position}:::${adapter.courses[position]}")
        Toast.makeText(context, "tarjeta Clickeada $position", Toast.LENGTH_LONG).show()

//        // Instancias un fragment, aquí se envían los datos, suponiendo que se llame FragmentDetalle:
        val f: DetailCourseFragment =
            DetailCourseFragment.newInstance(position, adapter.courses[position])
        val fragmentManager: FragmentManager? = fragmentManager
        fragmentManager?.beginTransaction()?.replace(R.id.fragmentListActivePrograms, f)
            ?.addToBackStack("atras")?.commit()
    }


//    override fun onItemClick(position: Int) {
//        Log.i("msg*****", " onItemClick(position: Int) onItemClick(position: Int) ${position}")
//        Toast.makeText(context, "tarjeta Clickeada $position", Toast.LENGTH_SHORT).show()
////
//        // obtienes la posicion del row clickeado:
//
//        // obtienes la posicion del row clickeado:
//        val clickedItem: Contenido = obtenerDatos().get(position)
//
//        // Instancias un fragment, aquí se envían los datos, suponiendo que se llame FragmentDetalle:
//
//        // Instancias un fragment, aquí se envían los datos, suponiendo que se llame FragmentDetalle:
//        val f: FragmentDetalle =
//            FragmentDetalle.newInstance(clickedItem.getTitulo(), clickedItem.getFoto())
//        val fragmentManager: FragmentManager? = fragmentManager
//        fragmentManager.beginTransaction().replace(R.id.container, f).addToBackStack(null).commit()

//}
}
