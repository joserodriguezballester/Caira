package com.example.caira2.iu.activity.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.caira2.R
import com.example.caira2.databinding.FragmentDetailCourseBinding
import com.example.caira2.iu.adapter.AdapterActiveProgram
import com.example.caira2.model.Course
import com.example.caira2.network.BASE_IMG_URL


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private var valor = true

/**
 * A simple [Fragment] subclass.
 * Use the [DetailCourseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailCourseFragment : Fragment(), AdapterActiveProgram.ItemClickListener {
    private var position: Int? = null
    private lateinit var course: Course

    //  private lateinit var centre: Center
    private var _binding: FragmentDetailCourseBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: DetailCourseViewModel
    private val adapter = AdapterActiveProgram()

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(ARG_PARAM1)
            course = it.getSerializable(ARG_PARAM2) as Course
        }
        Log.i("msg*****", "creando CenterCoursesFragment")
        viewModel = ViewModelProvider(this)[DetailCourseViewModel::class.java]


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailCourseBinding.inflate(inflater, container, false)
        val view = binding.root

//        binding.close.setOnClickListener {
//            Log.i("msg*****", "  close.setOnClickListener")
//            parentFragmentManager.popBackStack()
//        }

        // llenar card Universidad
        with(binding.includeItemCenter) {//relacionar vista del item con los datos
            centre = course.center  //centre es la variable del XML

            val imgUrl = BASE_IMG_URL.plus(course.center.logo?.substring(1))
            Glide.with(view).load(imgUrl).into(imageView)

            buttonCourses.setOnClickListener {
                Log.i("msg*****", "click courses")
                binding.CardCourse.visibility = GONE
                //    listener.onRecyclerViewItemClick(holder.itemView,centers[position])
            }
            buttonInfo.setOnClickListener {
                Log.i("msg*****", "click buttonInfo")
                //    listener.onRecyclerViewItemClick(holder.itemView,centers[position])
            }
            buttonFeed.setOnClickListener {
                Log.i("msg*****", "click buttonFeed")
                //   listener.onRecyclerViewItemClick(holder.itemView,centers[position])
            }

        }

        // llenar card curso seleccionado
        with(binding) {
            name.text = course.name
            description.text = course.description
            type.text = course.category
            location.text = course.location
            duration.text = course.duration.toString()
            price.text = course.price.toString() + " € "
            //  val imgUrl = getString(R.string.baseImgURL) + course.image.substring(1)
            val imgUrl = BASE_IMG_URL.plus(course.image.substring(1))
            Glide.with(view).load(imgUrl).into(imageViewDetail);
//            close.setOnClickListener {
//                Log.i("msg*****", "  close.setOnClickListener")
//                parentFragmentManager.popBackStack();
//            }
            getNow.setOnClickListener {
                Toast.makeText(
                    context,
                    "tarjeta Clickeada getNow.setOnClickListener",
                    Toast.LENGTH_LONG
                ).show()
            }
        }


        // llenar reciclerView
        Log.i("msg*****", "  course.center_id${course.center.id}")
        viewModel.llenarDatos(course.center.id)
        viewModel.courses.observe(viewLifecycleOwner) { datos ->
            adapter.AdapterActiveProgram(datos, requireContext())
            binding.rvCourses.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                // adapter.AdapterActiveProgram(datos, requireContext())
                it.adapter = adapter
            }
            adapter.setClickListener(this)
        }

        return view
    }

    override fun onClick(view: View?, position: Int) {
        Log.i(
            "msg*****",
            " onClick(segunda tarjeta) ${position}:::${adapter.courses[position]}"
        )


//        // Instancias un fragment, aquí se envían los datos, suponiendo que se llame FragmentDetalle:

        val detailCourseFragment: DetailCourseFragment =
            newInstance(position, adapter.courses[position])


        //    val fragmentManager: FragmentManager? = fragmentManager
        val fragmentManager: FragmentManager? = parentFragmentManager
        fragmentManager?.beginTransaction()
            ?.replace(R.id.fragmentDashboard, detailCourseFragment)
            ?.addToBackStack(null)?.commit()
    }
}


