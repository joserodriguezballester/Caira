package com.example.caira2.iu.activity.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

class ListProgramFragment : Fragment(), AdapterActiveProgram.OnItemClickListener {
    private lateinit var viewModel: DashboardViewModel

    //  private var _binding: FragmentDashboardBinding? = null
    private var _binding: FragmentListProgramBinding? = null
    private val binding get() = _binding!!

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var recylerActivePrograms: RecyclerView

    // var activeProgramsList= mutableListOf<Course>()
    private val adaptador = AdapterActiveProgram()

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
        recylerActivePrograms = binding.reciclerIdPrograms
        recylerActivePrograms.apply {
            layoutManager = LinearLayoutManager(context)

        }
        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        viewModel.llenarDatos()
        //    llenarDatos()
        viewModel.text.observe(viewLifecycleOwner) {
            adaptador.AdapterActiverPrograms(it, requireContext())
            recylerActivePrograms.adapter = adaptador
        }
        //  adaptador.AdapterActiverPrograms(activeProgramsList,requireContext())
        //   recylerActivePrograms.adapter=adaptador
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListProgramFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            ListProgramFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                //    }
            }
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(context, "tarjeta Clickeada $position", Toast.LENGTH_SHORT).show()
//
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

    }
}
