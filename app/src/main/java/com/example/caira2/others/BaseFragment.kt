package com.example.caira2.others

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

open class BaseFragment <T : ViewBinding>(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {
    protected var _binding: T? = null
    protected val binding get() = _binding!!

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}