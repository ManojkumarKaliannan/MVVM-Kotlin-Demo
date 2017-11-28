package com.kotlinmvvmdemo.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View

import com.kotlinmvvmdemo.R
import com.kotlinmvvmdemo.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {

    lateinit var activitySubBinding:ActivitySubBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        setSupportActionBar(activitySubBinding.toolbar)
        getFragmentLayout();
    }

    private fun initDataBinding() {
        activitySubBinding= DataBindingUtil.setContentView(this,R.layout.activity_sub);
    }

    private fun getFragmentLayout()
    {
        val pickUpLocationMapsFragment = SubFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.frame, pickUpLocationMapsFragment)
        fragmentTransaction.commit()
    }
}
