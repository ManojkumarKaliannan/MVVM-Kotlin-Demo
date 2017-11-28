package com.kotlinmvvmdemo.InterfaceApi

import com.kotlinmvvmdemo.model.NameResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Colan Infotech.
 */
interface ApiInterface {
    //getAllCountries
    @get:GET("photos")
    val allListDetails: Call<List<NameResponse>>
}