package com.fevziomurtekin.deezer.core.ui

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
}