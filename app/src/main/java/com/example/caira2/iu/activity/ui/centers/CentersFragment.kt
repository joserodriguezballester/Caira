package com.example.caira2.iu.activity.ui.centers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.caira2.R
import com.example.caira2.databinding.FragmentCentersBinding
import com.example.caira2.iu.adapter.CentersAdapter
import com.example.caira2.iu.adapter.RecyclerViewCentersClickListener
import com.example.caira2.model.Center
import kotlinx.android.synthetic.main.fragment_centers.*


class CentersFragment : Fragment(), RecyclerViewCentersClickListener {

    private var _binding: FragmentCentersBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CentersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState
        : Bundle?
    ): View {
        Log.i("msg*****", "onCreateView")
        //   viewModel = ViewModelProvider(this)[CentersViewModel::class.java]
        _binding = FragmentCentersBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel.centers.observe(viewLifecycleOwner) { datos ->
            recycler_view_centers.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = CentersAdapter(datos,this)
            }
        }

        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("msg*****", "onCreate")
        viewModel = ViewModelProvider(this)[CentersViewModel::class.java]
        viewModel.getCenters()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onRecyclerViewItemClick(view: View, center: Center) {
        Log.i("msg*****", "click item en fragment")
        when (view.id)  {
            R.id.buttonFeed ->{
                Log.i("msg*****", "click item  R.id.buttonFeed")
            }
            R.id.buttonInfo ->{
                Log.i("msg*****", "click item  R.id.buttonInfo")
            }
            R.id.buttonCourses ->{
                Log.i("msg*****", "click item  R.id.buttonCourses")
            }
            else ->{
                Log.i("msg*****", "click item SIN ID")
            }
        }
    }

}