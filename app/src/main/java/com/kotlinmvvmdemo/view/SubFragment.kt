package com.kotlinmvvmdemo.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kotlinmvvmdemo.R
import com.kotlinmvvmdemo.databinding.FragmentSubBinding
import com.kotlinmvvmdemo.viewmodel.SubFragmentViewModel


class SubFragment : Fragment()
{

    internal lateinit var fragmentSubBinding: FragmentSubBinding
    internal lateinit var fragmentSubViewModel:SubFragmentViewModel




    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         super.onCreateView(inflater, container, savedInstanceState)

        fragmentSubBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_sub, container, false)

        fragmentSubViewModel = SubFragmentViewModel(context)
        fragmentSubBinding.setFragmentNameModel(fragmentSubViewModel)

        return fragmentSubBinding.getRoot()


    }



}
