package com.example.caira2.iu.activity.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.caira2.databinding.FragmentDetailCourseBinding
import com.example.caira2.model.Course


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailCourseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailCourseFragment : Fragment() {
    private var position: Int? = null
    private lateinit var course: Course
    private var _binding: FragmentDetailCourseBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(ARG_PARAM1)
            course = it.getSerializable(ARG_PARAM2) as Course

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailCourseBinding.inflate(inflater, container, false)
        val view = binding.root

        with(binding) {
            name.text = course.name
            description.text = "Falta campo descripcion "
            type.text = course.category
            location.text = "Falta campo location"
            duration.text = course.duration.toString()
            price.text = course.price.toString() + " € "
            close.setOnClickListener {
                Log.i("msg*****", "  close.setOnClickListener")
                getFragmentManager()?.popBackStack();
            }
            getNow.setOnClickListener {
                Toast.makeText(context, "tarjeta Clickeada getNow.setOnClickListener", Toast.LENGTH_LONG).show()
            }
        }

        return view
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: Int, param2: Course) =
            DetailCourseFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putSerializable(ARG_PARAM2, param2)
                }
            }
    }
}