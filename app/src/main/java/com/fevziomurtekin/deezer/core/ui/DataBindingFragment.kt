package com.fevziomurtekin.deezer.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class DataBindingFragment : Fragment() {

    protected inline fun <reified T : ViewDataBinding> binding(
        inflater:LayoutInflater,
        @LayoutRes resId: Int,
        container:ViewGroup?
    ): T =  DataBindingUtil.inflate(inflater, resId,container,false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getSafeArgs()
        initBinding()
        setListeners()
        observeLiveData()
    }

    abstract fun getSafeArgs()

    abstract fun initBinding()

    abstract fun setListeners()

    abstract fun observeLiveData()

}
