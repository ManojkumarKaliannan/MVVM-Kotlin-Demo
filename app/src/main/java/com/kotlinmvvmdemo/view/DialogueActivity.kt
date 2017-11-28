package com.kotlinmvvmdemo.view

import android.app.Dialog
import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater

import com.kotlinmvvmdemo.R
import com.kotlinmvvmdemo.adapter.PeopleAdapter
import com.kotlinmvvmdemo.databinding.ActivityDialogueBinding
import com.kotlinmvvmdemo.viewmodel.DialogueViewModel
import com.kotlinmvvmdemo.viewmodel.NameViewModel
import java.security.AccessController.getContext
import java.util.*

class DialogueActivity(internal var context: Context) : Dialog(context), Observer {

    internal lateinit var fragmentMainBinding: ActivityDialogueBinding
    internal lateinit var dialogueViewModel: DialogueViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentMainBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.activity_dialogue, null, false)
        dialogueViewModel = DialogueViewModel(getContext())
        fragmentMainBinding.dialogueViewModel=dialogueViewModel
        setContentView(fragmentMainBinding.getRoot())
        setupListPeopleView(fragmentMainBinding.listDialoguepeople)
        setupObserver(dialogueViewModel)
    }

    private fun setupObserver(observable: Observable) {
        observable.addObserver(this)
    }

    private fun setupListPeopleView(listDialoguepeople: RecyclerView) {
        val adapter = PeopleAdapter()
        listDialoguepeople.adapter = adapter
        listDialoguepeople.layoutManager = LinearLayoutManager(context)
    }

    override fun update(observable: Observable?, arg: Any?)
    {
        if (observable is DialogueViewModel) {
            val peopleAdapter = fragmentMainBinding.listDialoguepeople.adapter as PeopleAdapter
            val nameViewModel = observable as DialogueViewModel
            peopleAdapter.setPeopleList(dialogueViewModel.getPeopleList())
        }
    }
}

