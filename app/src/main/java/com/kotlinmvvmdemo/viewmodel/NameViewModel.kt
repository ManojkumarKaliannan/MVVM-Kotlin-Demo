package com.kotlinmvvmdemo.viewmodel

import android.content.Context
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.util.Log
import android.view.View
import android.widget.Toast
import com.kotlinmvvmdemo.InterfaceApi.ApiInterface

import com.kotlinmvvmdemo.R
import com.kotlinmvvmdemo.webservice.WebServiceClient
import com.kotlinmvvmdemo.model.NameResponse

import java.util.ArrayList
import java.util.Observable

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**`
 * Created by Colan Infotech.
 */

class NameViewModel(private val context: Context) : Observable() {

    var peopleLabel: ObservableInt
    var messageLabel: ObservableField<String>
    var peopleProgress: ObservableInt
    var peopleRecycler: ObservableInt
    var peopleList = ArrayList<NameResponse>()

    init {
        this.peopleList = ArrayList()
        peopleLabel = ObservableInt(View.VISIBLE)
        peopleProgress = ObservableInt(View.VISIBLE)
        peopleRecycler = ObservableInt(View.GONE)
        messageLabel = ObservableField(context.getString(R.string.app_name))
        getListResponse()
    }

    private fun getListResponse()
    {
        val apiInterface = WebServiceClient.client.create(ApiInterface::class.java)
        val call = apiInterface.allListDetails
        call.enqueue(object : Callback<List<NameResponse>> {
            override fun onResponse(call: Call<List<NameResponse>>, response: Response<List<NameResponse>>) {

                if (response.body() != null) {


                    peopleProgress.set(View.GONE)
                    peopleLabel.set(View.GONE)
                    peopleRecycler.set(View.VISIBLE)
                    changePeopleDataSet(response.body())
                    Toast.makeText(context, "sucess", Toast.LENGTH_SHORT).show()
                } else {
                    peopleProgress.set(View.GONE)
                    Toast.makeText(context, "failure1", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<NameResponse>>, t: Throwable) {
                peopleProgress.set(View.GONE)
                Toast.makeText(context, "failure2", Toast.LENGTH_SHORT).show()
                Log.d("erorr", t.message)
            }
        })
    }

    private fun changePeopleDataSet(body: List<NameResponse>?) {

        if (body != null) {
            peopleList.addAll(body)
            setChanged()
            notifyObservers()
        };
    }

    fun getPeopleList(): List<NameResponse> {
        return peopleList
    }
}
