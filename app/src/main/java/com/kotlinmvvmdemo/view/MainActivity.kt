package com.kotlinmvvmdemo.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.kotlinmvvmdemo.R
import com.kotlinmvvmdemo.adapter.PeopleAdapter
import com.kotlinmvvmdemo.databinding.ActivityMainBinding
import com.kotlinmvvmdemo.viewmodel.NameViewModel

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), Observer {


    internal lateinit var activityMainBinding: ActivityMainBinding
    internal lateinit var nameViewModel: NameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        setSupportActionBar(activityMainBinding.toolbar)
        setupObserver(nameViewModel)
        setupListPeopleView(activityMainBinding.listPeople)

    }




    private fun initDataBinding() {
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        nameViewModel = NameViewModel(this)
        activityMainBinding.nameViewModel = nameViewModel
    }
    private fun setupListPeopleView(listPeople: RecyclerView) {
        val adapter = PeopleAdapter()
        listPeople.adapter = adapter
        listPeople.layoutManager = LinearLayoutManager(this)
    }

    private fun setupObserver( observable:Observable) {

        observable.addObserver(this)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun update(observable: Observable?, arg: Any?)
    {
        if (observable is NameViewModel) {
            val peopleAdapter = activityMainBinding.listPeople.adapter as PeopleAdapter
            val nameViewModel = observable as NameViewModel
            peopleAdapter.setPeopleList(nameViewModel.getPeopleList())
        }
    }
}
