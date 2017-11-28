package com.kotlinmvvmdemo.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kotlinmvvmdemo.R
import com.kotlinmvvmdemo.databinding.ItemPeopleBinding
import com.kotlinmvvmdemo.model.NameResponse
import com.kotlinmvvmdemo.viewmodel.IteamViewModel

/**
 * Created by Colan Infotech.
 */
class PeopleAdapter : RecyclerView.Adapter<PeopleAdapter.DateViewHolder>() {

    private var peopleList: List<NameResponse>? = null
    internal lateinit var iteamViewModel: ItemPeopleBinding
    init {
        this.peopleList = emptyList<NameResponse>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {

         iteamViewModel = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_people,
                parent, false)


        return DateViewHolder(iteamViewModel)

    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.bindPeople(peopleList!![position])
    }

    override fun getItemCount(): Int {
        return peopleList!!.size
    }

    fun setPeopleList(peopleList: List<NameResponse>) {
        this.peopleList = peopleList
        notifyDataSetChanged()

    }

    inner class DateViewHolder(internal var itemPeopleBinding: ItemPeopleBinding) : RecyclerView.ViewHolder(itemPeopleBinding.itemPeople) {

        fun bindPeople(nameResponse: NameResponse) {
            if (itemPeopleBinding.getPeopleViewModel() == null) {
                itemPeopleBinding.setPeopleViewModel(
                        IteamViewModel(nameResponse, itemView.context))
            } else {
                itemPeopleBinding.getPeopleViewModel()!!.setPeople(nameResponse)
            }
        }
    }
}