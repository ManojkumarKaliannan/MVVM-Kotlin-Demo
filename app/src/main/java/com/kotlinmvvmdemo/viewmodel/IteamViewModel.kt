package com.kotlinmvvmdemo.viewmodel

import android.content.Context
import android.content.Intent
import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView


import com.bumptech.glide.Glide
import com.kotlinmvvmdemo.model.NameResponse
import com.kotlinmvvmdemo.view.SubActivity

import java.util.Observable

/**
 * Created by Colan Infotech.
 */

class IteamViewModel(private var people: NameResponse?, private val context: Context) : Observable() {


    val fullName: String?
        get() = people!!.title


    val mail: String?
        get() = people!!.thumbnailUrl

    val pictureProfile: String?
        get() = people!!.url

    fun setPeople(people: NameResponse) {
        this.people = people

    }
    companion object {
        @JvmStatic @BindingAdapter("imageUrl")
        fun setImageUrl(imageView: ImageView, url: String) {
            Glide.with(imageView.context).load(url).into(imageView)
        }
    }
    fun onItemClick(view: View)
    {
        val intent = Intent(context, SubActivity::class.java)
        context.startActivity(intent)
    }


}
