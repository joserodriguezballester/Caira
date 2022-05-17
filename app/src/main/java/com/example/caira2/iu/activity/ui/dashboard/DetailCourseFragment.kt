package com.example.caira2.iu.activity.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.caira2.R
import com.example.caira2.databinding.FragmentDetailCourseBinding
import com.example.caira2.iu.adapter.AdapterCourse
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
class DetailCourseFragment : Fragment(), AdapterCourse.ItemClickListener {
    private var position: Int? = null
    private lateinit var course: Course

    //  private lateinit var centre: Center
    private var _binding: FragmentDetailCourseBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: DetailCourseViewModel
    private val adapter = AdapterCourse()

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
                binding.CardInfo?.visibility = GONE
                binding.CardCourse.visibility = GONE
                binding.rvCourses.visibility = VISIBLE
                //    listener.onRecyclerViewItemClick(holder.itemView,centers[position])
            }
            buttonInfo.setOnClickListener {
                Log.i("msg*****", "click buttonInfo")
                binding.CardCourse.visibility = GONE
                binding.rvCourses.visibility = GONE
                binding.infoDescription?.text =
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque viverra dolor fringilla orci aliquam blandit. In nec varius leo, ut faucibus nibh. Donec tincidunt, justo non cursus molestie, elit enim elementum eros, quis aliquam magna dui non libero. Nulla facilisi. Ut vel lacus in ligula commodo efficitur. Donec sit amet nunc velit. Mauris vel lectus ut metus ultricies posuere eu non turpis. Praesent placerat quam a sodales hendrerit. Proin sodales dolor sed nibh interdum aliquet sit amet a ligula. Aliquam nisi sapien, laoreet sed commodo et, tempus ut enim. Morbi sit amet imperdiet urna.\n" +
                            "\n" +
                            "Cras lorem nisi, porttitor et eros in, imperdiet sodales sem. Praesent ornare tincidunt est, scelerisque consequat eros hendrerit at. Sed interdum finibus sapien at blandit. Praesent lobortis leo ac magna viverra lacinia. Curabitur varius posuere quam id bibendum. Nunc ultrices turpis vel laoreet rhoncus. Fusce egestas molestie sapien, et fringilla urna maximus at. Fusce commodo varius mauris. Fusce mattis condimentum ante vitae bibendum. Maecenas imperdiet felis nisi, sed ultricies tellus mollis id. Nam fermentum mauris dolor, tincidunt convallis sapien sodales in. Quisque tristique varius felis, vitae hendrerit magna faucibus quis. Donec ut urna sed dolor congue mollis. Nam eget urna porttitor, pharetra augue eget, imperdiet orci. Duis eget nisi felis.\n" +
                            "\n" +
                            "Nunc leo leo, scelerisque et volutpat at, rhoncus et purus. Curabitur consectetur ut risus a blandit. Interdum et malesuada fames ac ante ipsum primis in faucibus. Sed aliquam interdum tellus, eget porttitor lectus accumsan nec. Curabitur in viverra arcu. Duis placerat gravida augue non posuere. Donec odio mi, hendrerit at dolor vitae, scelerisque lobortis metus. Fusce laoreet diam ac nunc fermentum, ac eleifend eros fermentum. "
                binding.CardInfo?.visibility = VISIBLE
                //    listener.onRecyclerViewItemClick(holder.itemView,centers[position])
            }
            buttonFeed.setOnClickListener {
                Log.i("msg*****", "click buttonFeed")
                binding.CardCourse.visibility = GONE
                binding.rvCourses.visibility = GONE
                binding.infoTitulo?.text = "FEED"
                binding.infoDescription?.text =
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque viverra dolor fringilla orci aliquam blandit. In nec varius leo, ut faucibus nibh. Donec tincidunt, justo non cursus molestie, elit enim elementum eros, quis aliquam magna dui non libero. Nulla facilisi. Ut vel lacus in ligula commodo efficitur. Donec sit amet nunc velit. Mauris vel lectus ut metus ultricies posuere eu non turpis. Praesent placerat quam a sodales hendrerit. Proin sodales dolor sed nibh interdum aliquet sit amet a ligula. Aliquam nisi sapien, laoreet sed commodo et, tempus ut enim. Morbi sit amet imperdiet urna.\n"
                binding.CardInfo?.visibility = VISIBLE
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
            val imgUrl = BASE_IMG_URL.plus(course.image?.substring(1))
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
            adapter.AdapterCourse(datos, requireContext())
            binding.rvCourses.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                // adapter.AdapterCourse(datos, requireContext())
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


