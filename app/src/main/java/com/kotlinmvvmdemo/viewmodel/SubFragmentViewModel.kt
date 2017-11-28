package com.kotlinmvvmdemo.viewmodel

import android.content.Context
import android.view.View
import android.widget.Toast
import com.kotlinmvvmdemo.view.DialogueActivity
import java.util.*

/**
 * Created by Colan Infotech.
 */
class SubFragmentViewModel(internal var context: Context) : Observable() {



    fun onClickLoad(view: View) {


        val mydialog = DialogueActivity(context)
        mydialog.setTitle("PopupList")
         mydialog.show()



    }



}