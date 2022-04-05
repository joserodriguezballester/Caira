package com.example.caira2.iu.activity.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.caira2.R
import com.example.caira2.iu.adapter.AdapterActiveProgram
import com.example.caira2.model.ActiveProgram

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListProgramFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListProgramFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var recylerActivePrograms:RecyclerView
    var activeProgramsList= mutableListOf<ActiveProgram>()
    private val adaptador= AdapterActiveProgram()

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
        var vista:View = inflater.inflate(R.layout.fragment_list_program, container, false)

        recylerActivePrograms= vista.findViewById(R.id.reciclerIdPrograms)
        recylerActivePrograms.layoutManager=LinearLayoutManager(context)
        llenarDatos()
        adaptador.AdapterActiverPrograms(activeProgramsList,requireContext())
        recylerActivePrograms.adapter=adaptador

        return vista
    }

    private fun llenarDatos() {

        activeProgramsList.add(ActiveProgram("MASTER OF COMPUTER SCIENCE","University of Upstate at Brookstone",R.drawable.uni_oxford))
        activeProgramsList.add(ActiveProgram("MASTER OF MEDICINE","University of Upstate at Massachuches",R.drawable.uni_masachuches))
        activeProgramsList.add(ActiveProgram("MASTER OF STACKOVERFLOW","University of Sant Google",R.drawable.uni_upv))
        activeProgramsList.add(ActiveProgram("MASTER OF MEDICINE","University of Upstate at Massachuches",R.drawable.uni_masachuches))
        activeProgramsList.add(ActiveProgram("MASTER OF COMPUTER SCIENCE","University of Upstate at Brookstone",R.drawable.uni_oxford))
        activeProgramsList.add(ActiveProgram("MASTER OF MEDICINE","University of Upstate at Massachuches",R.drawable.uni_masachuches))
        activeProgramsList.add(ActiveProgram("MASTER OF STACKOVERFLOW","University of Sant Google",R.drawable.uni_upv))
        activeProgramsList.add(ActiveProgram("MASTER OF MEDICINE","University of Upstate at Massachuches",R.drawable.uni_masachuches))


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
}