package com.example.currencychallenge.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.currencychallenge.MainActivity

abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    private var _binding: VB? = null
    protected val binding get() = _binding!!

    fun showSnackBarMsg(msg: String) {
        (requireActivity() as MainActivity).showSnackBar(msg)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding(inflater, container, savedInstanceState)
        myInit()
        myListeners()
        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    abstract fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): VB

    abstract fun myInit()
    abstract fun myListeners()
}